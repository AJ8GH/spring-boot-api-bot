package com.aj.esa;

import com.aj.domain.esa.AuthenticationMessage;
import com.aj.domain.esa.SubscriptionMessage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageFactoryTest {

    @Test
    void authenticationMessage() {
        MessageFactory messageFactory = new MessageFactory();
        AuthenticationMessage message = messageFactory
                .authenticationMessage("appKey", "token");

        assertEquals("appKey", message.getAppKey());
        assertEquals("token", message.getSession());
        assertEquals("authentication", message.getOp());
    }

    @Test
    void marketSubscriptionMessage() {
        MessageFactory messageFactory = new MessageFactory();
        SubscriptionMessage message = messageFactory
                .marketSubscriptionMessage("1.2345");

        assertEquals("1.2345", message.getMarketFilter()
                .get("marketIds")
                .get(0));

        assertEquals("marketSubscription", message.getOp());
        assertTrue(message.getMarketDataFilter().get("fields").contains("EX_BEST_OFFERS_DISP"));
        assertTrue(message.getMarketDataFilter().get("fields").contains("EX_BEST_OFFERS"));
        assertTrue(message.getMarketDataFilter().get("fields").contains("EX_MARKET_DEF"));
    }
}
