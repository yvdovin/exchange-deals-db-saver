package org.example.saver.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class ExchangeDealsInputPacket {
    List<ExchangeDeal> exchangeDeals;
}
