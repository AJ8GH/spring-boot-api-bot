package com.aj.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CancelExecutionReportTest {

    @Test
    void testCancelExecutionReport() {
        List<InstructionReport> reports = List.of(new InstructionReport());
        CancelExecutionReport report = new CancelExecutionReport(9L, "status", "1.23",
                "eventName", "marketName", reports);

        assertEquals(9L, report.getId());
        assertEquals("status", report.getStatus());
        assertEquals("1.23", report.getMarketId());
        assertEquals("marketName", report.getMarketName());
        assertEquals("eventName", report.getEventName());
    }
}
