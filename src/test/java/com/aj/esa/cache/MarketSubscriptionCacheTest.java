package com.aj.esa.cache;

import com.aj.domain.esa.ResponseMessage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MarketSubscriptionCacheTest {
    @Test
    public void testAddMessage() {
        MarketSubscriptionCache cache = new MarketSubscriptionCache();
        ResponseMessage message = ResponseMessage.builder()
                .id(99L)
                .build();

        cache.addMessage(message);
        ResponseMessage cachedMessage = cache.getMessage(99L);

        assertEquals(cachedMessage, message);
    }
}
