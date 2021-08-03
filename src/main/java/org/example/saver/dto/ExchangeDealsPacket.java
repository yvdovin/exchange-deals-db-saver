package org.example.saver.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ExchangeDealsPacket {
    List<ExchangeDeal> exchangeDeals;
}
