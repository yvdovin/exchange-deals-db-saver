package org.example.saver.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import java.io.Serializable;

@Getter
@Setter
public class OffsetDataId implements Serializable {

    private String serviceName;

    private String dataThreadName;
}
