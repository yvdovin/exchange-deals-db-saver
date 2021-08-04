package org.example.saver.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Биржевая сделка
 */
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true)
@Entity
@Table(name = "exchange_deals")
public class ExchangeDeal {

    /**
     * Guid сделки
     */
    @Id
    private UUID guid;

    private String tradeNum;

    /**
     * Guid cчета
     */
    private UUID accountGuid;

    /**
     * Guid типа сделки
     */
    private UUID exchangeTypeGuid;

    /**
     * Направление сделки
     */
    @Enumerated(EnumType.STRING)
    private DealDirection direction;

    /**
     * Guid площадки
     */
    private UUID placeGuid;

    /**
     * Guid торговой сессии, в рамках которой подано данное поручение
     */
    private UUID tradeSessionGuid;

    /**
     * Время заключения сделки
     */
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH-mm-ss")
    private LocalDateTime dealDateTime;

    /**
     * ФИ
     */
    private UUID instrumentGuid;

    /**
     * За валюту
     */
    private UUID currencyGuid;

    /**
     * Количество
     */
    private BigDecimal quantity;

    /**
     * Цена
     */
    private BigDecimal price;

    /**
     * На сумму
     */
    private BigDecimal volume;

    /**
     * Валюта НКД
     */
    private UUID couponCurrencyGuid;

    /**
     * Сумма НКД
     */
    private BigDecimal couponVolume;

    /**
     * Дата поставки инструмента
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate planDeliveryDate;

    /**
     * Дата расчетов (оплаты)
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate planPaymentDate;

    /**
     * кличество изменений сделки
     */
    private int revision;


}

