package org.example.saver.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@Table(name = "OFFSET_DATA")
@Accessors(chain = true)
public class OffsetData {
    @Id
    private String serviceName;
    @Id
    private String dataThreadName;
    private long offset;

}
