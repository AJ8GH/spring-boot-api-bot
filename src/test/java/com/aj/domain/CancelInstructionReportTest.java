package com.aj.domain;

import com.aj.domain.bettingtypes.CancelInstructionReport;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CancelInstructionReportTest {

    @Test
    void instructionReport() {
        CancelInstructionReport report = CancelInstructionReport.builder()
                .betId("123")
                .cancelledDate("date")
                .runnerName("runner")
                .id(1L)
                .sizeCancelled(0.5)
                .build();

        assertTrue(report.toString().contains(report.getBetId()));
        assertTrue(report.toString().contains(report.getCancelledDate()));
        assertTrue(report.toString().contains(report.getRunnerName()));
        assertTrue(report.toString().contains(report.getSizeCancelled().toString()));
        assertTrue(report.toString().contains(report.getId().toString()));
    }
}
