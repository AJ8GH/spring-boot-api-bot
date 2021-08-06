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
        List<ExchangePrice> prices = new ArrayList<>();
        Runner runner = new Runner(9L, 22L, "name", 0.0,
                prices, prices, prices);

        assertEquals(9L, runner.getId());
        assertEquals(22L, runner.getSelectionId());
        assertEquals("name", runner.getRunnerName());
        assertEquals(0.0, runner.getHandicap());
        assertEquals(prices, runner.getAvailableToBack());
        assertEquals(prices, runner.getAvailableToLay());
        assertEquals(prices, runner.getTradedVolume());
    }

    @Test
    void testToString() {
        List<ExchangePrice> prices = new ArrayList<>();
        Runner runner = new Runner(9L, 22L, "name", 0.0,
                prices, prices, prices);
        String toString = runner.toString();

        assertTrue(toString.contains(runner.getRunnerName()));
        assertTrue(toString.contains(runner.getHandicap().toString()));
        assertTrue(toString.contains(runner.getId().toString()));
        assertTrue(toString.contains(runner.getSelectionId().toString()));
    }

    @Test
    void testEnrich() {
        Runner runner = new Runner();
        runner.setSelectionId(999L);

        Runner runner1 = new Runner();
        Runner runner2 = new Runner();
        Runner runner3 = new Runner();

        runner2.setSelectionId(999L);
        runner2.setRunnerName("New Runner Name");

        List<Runner> runnerList = new ArrayList<>();

        runnerList.add(runner1);
        runnerList.add(runner2);
        runnerList.add(runner3);

        RunnerRepository repository = mock(RunnerRepository.class);
        when(repository.findAll()).thenReturn(runnerList);

        Runner.enrich(runner, repository);

        assertEquals("New Runner Name", runner.getRunnerName());
    }
}
