package com.aj.models;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MarketBookTest {

    @Test
    void testMarketBook() {
        List<Runner> runners = new ArrayList<>();
        MarketBook marketBook = MarketBook.builder()
                .id(9L)
                .marketId("1.23")
                .marketName("name")
                .status("status")
                .complete(true)
                .numberOfActiveRunners(10)
                .totalMatched(0.0)
                .totalAvailable(1.1)
                .eventName("event")
                .eventTypeName("eventType")
                .competitionName("competition")
                .build();

        assertTrue(marketBook.toString().contains(marketBook.getMarketId()));
        assertTrue(marketBook.toString().contains(marketBook.getStatus()));
        assertTrue(marketBook.toString().contains(marketBook.getMarketName()));
        assertTrue(marketBook.toString().contains(marketBook.getComplete().toString()));
        assertTrue(marketBook.toString().contains(marketBook.getId().toString()));
        assertTrue(marketBook.toString().contains(marketBook.getTotalMatched().toString()));
        assertTrue(marketBook.toString().contains(marketBook.getNumberOfActiveRunners().toString()));
        assertTrue(marketBook.toString().contains(marketBook.getTotalAvailable().toString()));
    }
}
