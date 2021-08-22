package com.aj.domain.bettingtypes;

import com.aj.domain.bettingenums.ExecutionReportErrorCode;
import com.aj.domain.bettingenums.ExecutionReportStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CancelExecutionReport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private ExecutionReportStatus status;
    private ExecutionReportErrorCode errorCode;
    private String marketId;
    private String eventName;
    private String marketName;
    @OneToMany(cascade = CascadeType.ALL)
    private List<CancelInstructionReport> instructionReports;
}
