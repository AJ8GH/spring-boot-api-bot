package com.aj.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @Test
    void testToString() {
        Event event = new Event(9L, "name", "countryCode",
                "timeZone", "openDate", 30);

        assertTrue(event.toString().contains(event.getId().toString()));
        assertTrue(event.toString().contains(event.getName()));
        assertTrue(event.toString().contains(event.getCountryCode()));
        assertTrue(event.toString().contains(event.getTimezone()));
        assertTrue(event.toString().contains(event.getMarketCount().toString()));
        assertTrue(event.toString().contains(event.getOpenDate()));
    }

    @Test
    void testEvent() {
        Event event = new Event(9L, "name", "countryCode",
                "timeZone", "openDate", 30);

        assertEquals(9L, event.getId());
        assertEquals("name", event.getName());
        assertEquals("countryCode", event.getCountryCode());
        assertEquals("timeZone", event.getTimezone());
        assertEquals("openDate", event.getOpenDate());
        assertEquals(30, event.getMarketCount());
    }
}
