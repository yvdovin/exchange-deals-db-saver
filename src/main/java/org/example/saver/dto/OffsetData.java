package org.example.saver.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

@Setter
@Getter
@ToString
@Entity
@Table(name = "offset_data")
@Accessors(chain = true)
@IdClass(OffsetDataId.class)
public class OffsetData implements Serializable {
    @Id
    private String serviceName;
    @Id
    private String dataThreadName;
    private long offsetValue;
}
