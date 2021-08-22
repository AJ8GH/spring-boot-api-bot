package com.aj.domain;

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
