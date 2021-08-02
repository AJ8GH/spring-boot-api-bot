package com.aj.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventTypeTest {

    @Test
    void testToString() {
        EventType eventType = new EventType(9L, "Tennis", 99);

        assertTrue(eventType.toString().contains(eventType.getId().toString()));
        assertTrue(eventType.toString().contains(eventType.getName()));
        assertTrue(eventType.toString().contains(eventType.getMarketCount().toString()));
    }

    @Test
    void testEventType() {
        EventType eventType = new EventType(9L, "Tennis", 99);

        assertEquals("Tennis", eventType.getName());
        assertEquals(99, eventType.getMarketCount());
        assertEquals(9L, eventType.getId());
    }
}
