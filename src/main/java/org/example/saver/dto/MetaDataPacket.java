package org.example.saver.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
public class MetaDataPacket {
    /**
     * Идентификатор шарда
     */
    private String serviceName;
    /**
     * Мапа потока данных и соответствующего ему оффсета
     */
    private Map<String, Long> offsetMap;
}
