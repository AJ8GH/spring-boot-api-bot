package com.aj.models;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MarketCatalogueTest {

    @Test
    void testMarketCatalogue() {
        List<Runner> runners = new ArrayList<>();
        MarketCatalogue marketCatalogue = new MarketCatalogue(9L, "1.23",
                "name", 0.0, runners, new EventType(), new Event(), new Competition());

        assertEquals("1.23", marketCatalogue.getMarketId());
        assertEquals("name", marketCatalogue.getMarketName());
        assertEquals(runners, marketCatalogue.getRunners());
        assertEquals(9L, marketCatalogue.getId());
        assertEquals(0.0, marketCatalogue.getTotalMatched());
    }

    @Test
    void testToString() {
        List<Runner> runners = new ArrayList<>();
        MarketCatalogue marketCatalogue = new MarketCatalogue(9L, "1.23",
                "name", 0.0, runners, new EventType(), new Event(), new Competition());

        String toString = marketCatalogue.toString();
        assertTrue(toString.contains(marketCatalogue.getMarketId()));
        assertTrue(toString.contains(marketCatalogue.getMarketName()));
        assertTrue(toString.contains(marketCatalogue.getId().toString()));
        assertTrue(toString.contains(marketCatalogue.getTotalMatched().toString()));
    }
}
