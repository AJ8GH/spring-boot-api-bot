package com.aj.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CancelExecutionReportTest {

    @Test
    public void testCancelExecutionReport() {
        CancelExecutionReport report = new CancelExecutionReport(
                9L, "status", "1.23", 2.0, "date");

        assertEquals(9L, report.getId());
        assertEquals("status", report.getStatus());
        assertEquals("1.23", report.getMarketId());
        assertEquals(2.0, report.getSizeCancelled());
        assertEquals("date", report.getCancelledDate());
    }
}
