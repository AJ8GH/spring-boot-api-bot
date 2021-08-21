package com.aj.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExchangePriceTest {

    @Test
    void testExchangePrice() {
        ExchangePrice exchangePrice = ExchangePrice.builder()
                .id(1L)
                .price(2.0)
                .size(5.0)
                .build();

        assertTrue(exchangePrice.toString().contains(exchangePrice.getPrice().toString()));
        assertTrue(exchangePrice.toString().contains(exchangePrice.getId().toString()));
        assertTrue(exchangePrice.toString().contains(exchangePrice.getSize().toString()));
    }
}
