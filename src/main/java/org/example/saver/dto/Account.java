package org.example.saver.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Setter
@Getter
@ToString
@Entity
@Table(name = "accounts")
@Accessors(chain = true)
public class Account {

    @Id
    private UUID accountGuid;

    private String shardName;

}
