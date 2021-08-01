package com.aj.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BetTest {

    @Test
    void testToString() {
        Bet bet = new Bet();

        String expectedOutput = "Bet{" +
                "betId=" + bet.getBetId() +
                ", marketId='" + bet.getMarketId() + '\'' +
                ", selectionId=" + bet.getSelectionId() +
                ", price=" + bet.getPrice() +
                ", size=" + bet.getSize() +
                ", side='" + bet.getSide() + '\'' +
                ", bspLiability=" + bet.getBspLiability() +
                ", status='" + bet.getStatus() + '\'' +
                '}';

        assertEquals(expectedOutput, bet.toString());
    }

    @Test void testBet() {
        Bet bet = new Bet("1.0", 2, 3.0, 4.0, "BACK", 5.0, "EXECUTABLE");

        assertNull(bet.getBetId());
        assertEquals("1.0", bet.getMarketId());
        assertEquals(2, bet.getSelectionId());
        assertEquals(3.0, bet.getPrice());
        assertEquals(4.0, bet.getSize());
        assertEquals("BACK", bet.getSide());
        assertEquals(5.0, bet.getBspLiability());
        assertEquals("EXECUTABLE", bet.getStatus());
    }
}
