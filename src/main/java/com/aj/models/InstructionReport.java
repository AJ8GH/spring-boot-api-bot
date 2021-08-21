package com.aj.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InstructionReport extends DateTimeParser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String status;
    private String betId;
    private String RunnerName;
    private Double sizeCancelled;
    private String cancelledDate;

    @JsonProperty("instruction")
    private void unpackBetId(Map<String, Object> instruction) {
        this.betId = (String) instruction.get("betId");
    }
}
