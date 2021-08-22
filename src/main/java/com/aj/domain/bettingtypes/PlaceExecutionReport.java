package com.aj.domain.bettingtypes;

import com.aj.domain.bettingenums.ExecutionReportErrorCode;
import com.aj.domain.bettingenums.ExecutionReportStatus;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class PlaceExecutionReport {
    private final String customerRef;
    private final ExecutionReportStatus status;
    private final ExecutionReportErrorCode errorCode;
    private final String marketId;
    private final List<PlaceInstructionReport> instructionReports;
}
