package com.aj.domain.bettingtypes;

import com.aj.domain.bettingenums.InstructionReportErrorCode;
import com.aj.domain.bettingenums.InstructionReportStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.Map;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CancelInstructionReport extends DateTimeParser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private InstructionReportStatus status;
    private InstructionReportErrorCode errorCode;
    private String betId;
    private String runnerName;
    private Double sizeCancelled;
    private String cancelledDate;
    // @OneToOne(cascade = CascadeType.ALL)
    // private CancelInstruction instruction;

    @JsonProperty("instruction")
    private void unpackBetId(Map<String, Object> instruction) {
        this.betId = (String) instruction.get("betId");
    }
}
