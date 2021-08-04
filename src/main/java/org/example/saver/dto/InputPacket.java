package org.example.saver.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class InputPacket {
    private ExchangeDealsInputPacket exchangeDealsInputPacket;
    private OperationsOutputPacket operationsOutputPacket;
    private MetaDataPacket metaDataPacket;
}
