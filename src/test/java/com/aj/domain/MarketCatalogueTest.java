package com.aj.domain;

import com.aj.domain.bettingtypes.MarketCatalogue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MarketCatalogueTest {

    @Test
    void testMarketCatalogue() {
        MarketCatalogue marketCatalogue = MarketCatalogue.builder()
                .id(9L)
                .marketId("1.23")
                .marketName("market")
                .competitionName("competition")
                .eventTypeName("eventType")
                .eventName("event")
                .totalMatched(0.0)
                .build();

        assertTrue(marketCatalogue.toString().contains(marketCatalogue.getMarketId()));
        assertTrue(marketCatalogue.toString().contains(marketCatalogue.getMarketName()));
        assertTrue(marketCatalogue.toString().contains(marketCatalogue.getId().toString()));
        assertTrue(marketCatalogue.toString().contains(marketCatalogue.getTotalMatched().toString()));
        assertTrue(marketCatalogue.toString().contains(marketCatalogue.getEventName()));
        assertTrue(marketCatalogue.toString().contains(marketCatalogue.getEventTypeName()));
        assertTrue(marketCatalogue.toString().contains(marketCatalogue.getCompetitionName()));
    }
}
