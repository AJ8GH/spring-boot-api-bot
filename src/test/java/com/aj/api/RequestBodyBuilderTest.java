package com.aj.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RequestBodyBuilderTest {

    @Test
    void testListEventTypesBody() {
        var requestBodyBuilder = new RequestBodyBuilder();
        String body = "{\"filter\":{}}";

        assertEquals(body, requestBodyBuilder.listEventTypesBody());
    }

    @Test
    void testListEventsBody() {
        RequestBodyBuilder requestBodyBuilder = new RequestBodyBuilder();

        long eventTypeId = 1L;
        String body = "{\"filter\":{\"eventTypeIds\":[" + eventTypeId + "]}}";

        assertEquals(body, requestBodyBuilder.listEventsBody(eventTypeId));
    }

    @Test
    void testListMarketCatalogueBody() {
        RequestBodyBuilder requestBodyBuilder = new RequestBodyBuilder();

        long eventId = 999L;
        String body = "{\"filter\":{\"eventIds\":[" + eventId +
                "]},\"marketProjection\": [\"RUNNER_DESCRIPTION\"]," +
                "\"maxResults\":\"200\"}";

        assertEquals(body, requestBodyBuilder.listMarketCatalogueBody(eventId));
    }

    @Test
    void testListMarketBookBody() {
        RequestBodyBuilder requestBodyBuilder = new RequestBodyBuilder();
        String marketId = "1.23456789";

        String body = "{\"marketIds\": [" + marketId + "],\"priceProjection\"" +
                ": {\"priceData\": [\"EX_BEST_OFFERS\", \"EX_TRADED\"]," +
                "\"virtualise\": \"true\"}}}";

        assertEquals(body, requestBodyBuilder.listMarketBookBody(marketId));
    }

    @Test

    void testListCurrentOrdersBody() {
        RequestBodyBuilder requestBodyBuilder = new RequestBodyBuilder();
        String body = "{\"orderProjection\": \"EXECUTABLE\"}";

        assertEquals(body, requestBodyBuilder.listCurrentOrdersBody());
    }

    @Test
    void testPlaceOrdersBody() {
        RequestBodyBuilder requestBodyBuilder = new RequestBodyBuilder();

        String marketId = "1.23456789";
        long selectionId = 2345L;
        String side = "BACK";
        double price = 5.0;
        double size = 7.0;

        String body = "{\"marketId\": \"" + marketId + "\"," +
                "\"instructions\": [{\"selectionId\": " + selectionId +
                ",\"side\": \"" + side + "\",\"orderType\": \"LIMIT\"," +
                "\"limitOrder\": {\"size\": " + size + "," +
                "\"price\": " + price + "}}]}";

        assertEquals(body, requestBodyBuilder.placeOrdersBody(
                marketId, selectionId, side, size, price));
    }

    @Test
    void testCancelOrdersBody() {
        var requestBodyBuilder = new RequestBodyBuilder();

        long betId = 234234L;
        String marketId = "1.837454";

        String body = "{\"marketId\": \"" + marketId + "\"," +
                "\"instructions\": [{\"betId\": " + betId + "," +
                "\"sizeReduction\": null}]}";

        assertEquals(body, requestBodyBuilder.cancelOrdersBody(marketId, betId));
    }
}
