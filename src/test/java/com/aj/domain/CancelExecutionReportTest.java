package com.aj.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CancelExecutionReportTest {

    @Test
    void testCancelExecutionReport() {
        CancelExecutionReport report = CancelExecutionReport.builder()
                .id(9L)
                .status("status")
                .marketId("1.23")
                .marketName("marketName")
                .eventName("eventName")
                .build();

        assertEquals(9L, report.getId());
        assertEquals("status", report.getStatus());
        assertEquals("1.23", report.getMarketId());
        assertEquals("marketName", report.getMarketName());
        assertEquals("eventName", report.getEventName());
    }
}
