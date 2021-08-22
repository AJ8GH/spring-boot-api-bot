package com.aj.domain;

import com.aj.domain.bettingtypes.EventType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventTypeTest {

    @Test
    void testToString() {
        EventType eventType = EventType.builder()
                .id(9L)
                .name("Tennis")
                .marketCount(99)
                .build();

        assertTrue(eventType.toString().contains(eventType.getId().toString()));
        assertTrue(eventType.toString().contains(eventType.getName()));
        assertTrue(eventType.toString().contains(eventType.getMarketCount().toString()));
    }
}
