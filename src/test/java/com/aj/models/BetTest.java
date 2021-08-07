package com.aj.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BetTest {

    @Test
    void testToString() {
        Bet bet = new Bet(1L, 0L, "1.0", 2L, "marketName",
                "runnerName", "2021-02-25T15:44:53.000Z", 3.0, 4.0,
                "BACK", 5.0, "EXECUTABLE");

        assertTrue(bet.toString().contains(bet.getBetId().toString()));
        assertTrue(bet.toString().contains(bet.getMarketId()));
        assertTrue(bet.toString().contains(bet.getSelectionId().toString()));
        assertTrue(bet.toString().contains(bet.getPrice().toString()));
        assertTrue(bet.toString().contains(bet.getSize().toString()));
        assertTrue(bet.toString().contains(bet.getSide()));
        assertTrue(bet.toString().contains(bet.getStatus()));
        assertTrue(bet.toString().contains(bet.getBspLiability().toString()));
        assertTrue(bet.toString().contains("2021-02-25T15:44:53.000Z"));

    }

    @Test void testBet() {
        Bet bet = new Bet(1L, 0L, "1.0", 2L, "marketName",
                "runnerName", "2021-02-25T15:44:53.000Z", 3.0, 4.0,
                "BACK", 5.0, "EXECUTABLE");

        assertEquals(1L, bet.getId());
        assertEquals(0L, bet.getBetId());
        assertEquals("1.0", bet.getMarketId());
        assertEquals(2L, bet.getSelectionId());
        assertEquals(3.0, bet.getPrice());
        assertEquals(4.0, bet.getSize());
        assertEquals("BACK", bet.getSide());
        assertEquals(5.0, bet.getBspLiability());
        assertEquals("EXECUTABLE", bet.getStatus());
        assertEquals("2021-02-25T15:44:53.000Z", bet.getPlacedDate());
    }
}
