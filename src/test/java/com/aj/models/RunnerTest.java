package com.aj.models;

import com.aj.repositories.MarketCatalogueRepository;
import com.aj.repositories.RunnerRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
        assertTrue(runner.toString().contains(runner.getHandicap().toString()));
        assertTrue(runner.toString().contains(runner.getId().toString()));
        assertTrue(runner.toString().contains(runner.getSelectionId().toString()));
    }
}
