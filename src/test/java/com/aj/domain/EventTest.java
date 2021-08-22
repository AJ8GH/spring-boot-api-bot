package com.aj.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @Test
    void testToString() {
        Event event = Event.builder()
                .id(9L)
                .name("name")
                .countryCode("countryCode")
                .timezone("timezone")
                .openDate("openDate")
                .marketCount(30)
                .build();

        assertTrue(event.toString().contains(event.getId().toString()));
        assertTrue(event.toString().contains(event.getName()));
        assertTrue(event.toString().contains(event.getCountryCode()));
        assertTrue(event.toString().contains(event.getTimezone()));
        assertTrue(event.toString().contains(event.getMarketCount().toString()));
    }
}
