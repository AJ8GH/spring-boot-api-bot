package com.aj.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExchangePriceTest {

    @Test
    void testExchangePrice() {
        ExchangePrice exchangePrice = new ExchangePrice(1L, 2.0, 5.0);

        assertEquals(1L, exchangePrice.getId());
        assertEquals(2.0, exchangePrice.getPrice());
        assertEquals(5.0, exchangePrice.getSize());
    }

    @Test
    void testToString() {
        ExchangePrice exchangePrice = new ExchangePrice(1L, 2.0, 5.0);
        String toString = exchangePrice.toString();

        assertTrue(toString.contains(exchangePrice.getPrice().toString()));
        assertTrue(toString.contains(exchangePrice.getId().toString()));
        assertTrue(toString.contains(exchangePrice.getSize().toString()));
    }
}
