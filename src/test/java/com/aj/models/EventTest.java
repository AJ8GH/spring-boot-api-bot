package com.aj.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @Test
    void testToString() {
        Event event = new Event();
        String expectedOutput = "Event{" +
                "id=" + event.getId() +
                ", name='" + event.getName() + '\'' +
                ", countryCode='" + event.getCountryCode() + '\'' +
                ", timezone='" + event.getTimezone() + '\'' +
                ", openDate='" + event.getOpenDate() + '\'' +
                ", marketCount=" + event.getMarketCount() +
                '}';

        assertEquals(expectedOutput, event.toString());
    }

    @Test
    void testEvent() {
        Event event = new Event("name", "countryCode",
                "timeZone", "openDate", 30);

        assertEquals("name", event.getName());
        assertEquals("countryCode", event.getCountryCode());
        assertEquals("timeZone", event.getTimezone());
        assertEquals("openDate", event.getOpenDate());
        assertEquals(30, event.getMarketCount());
    }
}
