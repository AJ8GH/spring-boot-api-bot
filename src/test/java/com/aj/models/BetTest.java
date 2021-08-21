package com.aj.models;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BetTest {

    @Test
    void testToString() {
        Bet bet = Bet.builder()
                .betId("123")
                .marketId("1.23")
                .side("BACK")
                .marketName("marketName")
                .status("EXECUTABLE")
                .placedDate("2021-02-25T15:44:53.000Z")
                .build();

        assertTrue(bet.toString().contains(bet.getBetId()));
        assertTrue(bet.toString().contains(bet.getMarketId()));
        assertTrue(bet.toString().contains(bet.getSide()));
        assertTrue(bet.toString().contains(bet.getStatus()));
        assertTrue(bet.toString().contains(bet.getPlacedDate()));

    }

    @Test
    void testFindByBetId() {
        Bet bet = Bet.builder().betId("123").build();
        Bet bet2 = Bet.builder().betId("456").build();
        Bet bet3 = Bet.builder().betId("789").build();

        Bet foundBet = Bet.findByBetId("456", List.of(bet, bet2, bet3));

        assertEquals(bet2, foundBet);
    }
}
