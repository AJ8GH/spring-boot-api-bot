package com.aj.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventTypeTest {

    @Test
    void testToString() {
        EventType eventType = new EventType();

        String expectedOutput = "EventType{" +
                "id=" + eventType.getId() +
                ", name='" + eventType.getName() + '\'' +
                ", marketCount=" + eventType.getMarketCount() +
                '}';

        assertEquals(expectedOutput, eventType.toString());
    }

    @Test
    void testEventType() {
        EventType eventType = new EventType("Tennis", 99);

        assertEquals("Tennis", eventType.getName());
        assertEquals(99, eventType.getMarketCount());
        assertNull(eventType.getId());
    }
}
