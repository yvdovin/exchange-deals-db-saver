package org.example.saver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.example.saver.dto.*;
import org.example.saver.repo.ExchangeDealsRepository;
import org.example.saver.repo.OffsetDataRepository;
import org.example.saver.repo.OperationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Worker {

    @Autowired
    ExchangeDealsRepository exchangeDealsRepository;

    @Autowired
    OperationsRepository operationsRepository;

    @Autowired
    OffsetDataRepository offsetDataRepository;

    @Autowired
    Consumer<String, String> consumer;

    @Autowired
    ObjectMapper objectMapper;

    @EventListener(ApplicationReadyEvent.class)
    public void work() {
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
            if (records.count() == 0) {
                continue;
            }
            List<ExchangeDeal> exchangeDeals = new ArrayList<>();
            List<Operation> operations = new ArrayList<>();
            List<MetaDataPacket> metaDataPackets = new ArrayList<>();
            records.forEach(record -> {
                try {
                    InputPacket inputPacket = objectMapper.readValue(record.value(), InputPacket.class);
                    exchangeDeals.addAll(inputPacket.getExchangeDealsInputPacket().getExchangeDeals());
                    operations.addAll(inputPacket.getOperationsOutputPacket().getOperations());
                    metaDataPackets.add(inputPacket.getMetaDataPacket());
                    System.out.println(inputPacket);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            });

            saveIntoDB(exchangeDeals, operations, filterOffsetData(metaDataPackets));
            consumer.commitSync();
        }
    }

    @Autowired
    private TransactionTemplate transactionTemplate;

    private void saveIntoDB(List<ExchangeDeal> exchangeDeals, List<Operation> operations, List<OffsetData> offsetDataList) {
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            public void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                exchangeDealsRepository.saveAll(exchangeDeals);
                operationsRepository.saveAll(operations);
                offsetDataRepository.saveAll(offsetDataList);
            }
        });
    }


    private List<OffsetData> filterOffsetData(List<MetaDataPacket> metaDataPackets) {
        Map<String, OffsetData> filteredOffsetData = new HashMap<>();
        for (MetaDataPacket metaDataPacket : metaDataPackets) {
            for (String s : metaDataPacket.getOffsetMap().keySet()) {
                OffsetData offsetData = new OffsetData()
                        .setServiceName(metaDataPacket.getServiceName())
                        .setDataThreadName(s)
                        .setOffsetValue(metaDataPacket.getOffsetMap().get(s));
                OffsetData currentFilteredOffsetData = filteredOffsetData.get(offsetData.getServiceName() + offsetData.getDataThreadName());
                if (currentFilteredOffsetData == null) {
                    filteredOffsetData.put(offsetData.getServiceName() + offsetData.getDataThreadName(), offsetData);
                } else if (currentFilteredOffsetData.getOffsetValue() < offsetData.getOffsetValue()) {
                    currentFilteredOffsetData.setOffsetValue(offsetData.getOffsetValue());
                }
            }
        }
        return new ArrayList<>(filteredOffsetData.values());
    }
}
