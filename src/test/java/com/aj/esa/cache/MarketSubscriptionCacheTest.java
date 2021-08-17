package com.aj.esa.cache;

import com.aj.esa.models.ResponseMessage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MarketSubscriptionCacheTest {
    @Test
    public void testAddMessage() {
        MarketSubscriptionCache cache = new MarketSubscriptionCache();
        ResponseMessage message = new ResponseMessage();
        message.setId(99L);
        cache.addMessage(message);
        ResponseMessage cachedMessage = cache.getMessage(99L);

        assertEquals(cachedMessage, message);
    }
}
