package org.example.saver.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Операция
 */
@Getter
@Setter
@Accessors(chain = true)
@ToString
@Entity
@Table(name = "operations")
public class Operation {

    @Id
    private UUID guid;

    /**
     * Guid cчета
     */
    private UUID accountGuid;

    /**
     * Время последнего изменнеия операции
     */
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH-mm-ss")
    private LocalDateTime updatedDateTime;

    /**
     * Guid площадки
     */
    private UUID placeGuid;

    /**
     * Guid торговой сессии
     */
    private UUID tradeSessionGuid;

    /**
     * Guid финансового инструмента
     */
    private UUID instrumentGuid;

    /**
     * Количество
     */
    private BigDecimal quantity;

    /**
     * Планируемая дата изменения инструмента
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate planDate;

    /**
     *  количество изменений операции
     */
    private int revision;


}
