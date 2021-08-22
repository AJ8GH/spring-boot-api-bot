package com.aj.domain;

import com.aj.domain.bettingtypes.Runner;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class RunnerTest {

    @Test
    void testRunner() {
        Runner runner = Runner.builder()
                .id(9L)
                .selectionId(22L)
                .runnerName("runner")
                .handicap(0.0)
                .build();

        assertTrue(runner.toString().contains(runner.getRunnerName()));
        assertTrue(runner.toString().contains(String.valueOf(runner.getHandicap())));
        assertTrue(runner.toString().contains(runner.getId().toString()));
        assertTrue(runner.toString().contains(String.valueOf(runner.getSelectionId())));
    }
}
