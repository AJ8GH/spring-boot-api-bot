package com.aj.models;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
}
