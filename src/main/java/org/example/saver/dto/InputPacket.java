package org.example.saver.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InputPacket {
    private ExchangeDealsPacket exchangeDealsPacket;
    private OperationsPacket operationsPacket;
    private MetaDataPacket metaDataPacket;
}
