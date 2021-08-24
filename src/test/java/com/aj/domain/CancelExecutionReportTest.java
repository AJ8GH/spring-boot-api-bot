package com.aj.domain;

import com.aj.domain.bettingenums.ExecutionReportErrorCode;
import com.aj.domain.bettingenums.ExecutionReportStatus;
import com.aj.domain.bettingtypes.CancelExecutionReport;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CancelExecutionReportTest {

    @Test
    void testCancelExecutionReport() {
        CancelExecutionReport report = CancelExecutionReport.builder()
                .id(9L)
                .status(ExecutionReportStatus.SUCCESS)
                .errorCode(ExecutionReportErrorCode.BET_ACTION_ERROR)
                .marketId("1.23")
                .marketName("marketName")
                .eventName("eventName")
                .build();

        assertEquals(9L, report.getId());
        assertEquals(ExecutionReportStatus.SUCCESS, report.getStatus());
        assertEquals(ExecutionReportErrorCode.BET_ACTION_ERROR, report.getErrorCode());
        assertEquals("1.23", report.getMarketId());
        assertEquals("marketName", report.getMarketName());
        assertEquals("eventName", report.getEventName());
    }
}
