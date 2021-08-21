package com.aj.models;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MarketCatalogueTest {

    @Test
    void testMarketCatalogue() {
        List<Runner> runners = new ArrayList<>();
        MarketCatalogue marketCatalogue = MarketCatalogue.builder()
                .id(9L)
                .marketId("1.23")
                .marketName("market")
                .competitionName("competition")
                .eventTypeName("eventType")
                .eventName("event")
                .totalMatched(0.0)
                .build();

        String toString = marketCatalogue.toString();
        assertTrue(marketCatalogue.toString().contains(marketCatalogue.getMarketId()));
        assertTrue(marketCatalogue.toString().contains(marketCatalogue.getMarketName()));
        assertTrue(marketCatalogue.toString().contains(marketCatalogue.getId().toString()));
        assertTrue(marketCatalogue.toString().contains(marketCatalogue.getTotalMatched().toString()));
        assertTrue(marketCatalogue.toString().contains(marketCatalogue.getEventName().toString()));
        assertTrue(marketCatalogue.toString().contains(marketCatalogue.getEventTypeName().toString()));
        assertTrue(marketCatalogue.toString().contains(marketCatalogue.getCompetitionName().toString()));
    }
}
