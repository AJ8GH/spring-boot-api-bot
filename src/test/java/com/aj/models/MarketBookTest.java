package com.aj.models;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MarketBookTest {

    @Test
    void testMarketBook() {
        List<Runner> runners = new ArrayList<>();
        MarketBook marketBook = new MarketBook(9L, "1.23", "name",
                "status", true, 10, 0.0, 1.1, runners);

        assertEquals(9L, marketBook.getId());
        assertEquals("1.23", marketBook.getMarketId());
        assertEquals("status", marketBook.getStatus());
        assertEquals(true, marketBook.getComplete());
        assertEquals(0.0, marketBook.getTotalMatched());
        assertEquals(1.1, marketBook.getTotalAvailable());
        assertEquals(10, marketBook.getNumberOfActiveRunners());
        assertEquals(runners, marketBook.getRunners());
    }

    @Test
    void testToString() {
        List<Runner> runners = new ArrayList<>();
        MarketBook marketBook = new MarketBook(9L, "1.23", "name",
                "status", true, 10, 0.0, 1.1, runners);

        String toString = marketBook.toString();

        assertTrue(toString.contains(marketBook.getMarketId()));
        assertTrue(toString.contains(marketBook.getStatus()));
        assertTrue(toString.contains(marketBook.getMarketName()));
        assertTrue(toString.contains(marketBook.getComplete().toString()));
        assertTrue(toString.contains(marketBook.getId().toString()));
        assertTrue(toString.contains(marketBook.getTotalMatched().toString()));
        assertTrue(toString.contains(marketBook.getNumberOfActiveRunners().toString()));
        assertTrue(toString.contains(marketBook.getTotalAvailable().toString()));
    }
}
