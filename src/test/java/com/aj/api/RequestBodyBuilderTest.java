package com.aj.api;

import com.aj.api.bettingTypes.MarketFilter;
import com.aj.api.bettingTypes.RequestBody;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class RequestBodyBuilderTest {

    private RequestBodyBuilder requestBodyBuilder;
    private ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        requestBodyBuilder = new RequestBodyBuilder(new ObjectMapper());
        mapper = new ObjectMapper();
    }

    @Test
    void testListEventTypesBody() {
        String body = "{\"filter\":{}}";

        assertEquals(body, requestBodyBuilder.listEventTypesBody());
    }

    @Test
    void testListEventsBody() throws JsonProcessingException {
        String eventTypeId = "1";

        MarketFilter filter = MarketFilter.builder()
                .eventTypeIds(Set.of(eventTypeId))
                .build();
        RequestBody requestBody = RequestBody.builder()
                .filter(filter)
                .build();

        String body = mapper.writeValueAsString(requestBody);

        assertEquals(body, requestBodyBuilder.listEventsBody(eventTypeId));
    }

    @Test
    void testListMarketCatalogueBody() {
        String eventId = "999";
        String filterType = "eventIds";
        String body = "{\"filter\":{\"" + filterType + "\":[\"" + eventId +
                "\"]},\"marketProjection\": [\"RUNNER_DESCRIPTION\", " +
                "\"COMPETITION\", \"EVENT\", \"EVENT_TYPE\"]," +
                "\"maxResults\":\"200\"}";

        assertEquals(body, requestBodyBuilder.listMarketCatalogueBody(filterType, eventId));
    }

    @Test
    void testListMarketBookBody() {
        String marketId = "1.23456789";

        String body = "{\"marketIds\": [\"" + marketId + "\"],\"priceProjection\"" +
                ": {\"priceData\": [\"EX_BEST_OFFERS\", \"EX_TRADED\"]," +
                "\"virtualise\": \"true\"}}}";

        assertEquals(body, requestBodyBuilder.listMarketBookBody(marketId));
    }

    @Test
    void testListCurrentOrdersBody() {
        String body = "{\"orderProjection\": \"EXECUTABLE\"}";

        assertEquals(body, requestBodyBuilder.listCurrentOrdersBody());
    }

    @Test
    void testListCurrentOrdersBodyWithBetId() {
        String body = "{\"orderProjection\": \"EXECUTABLE\",\"betIds\": [\"9292\"]}";

        assertEquals(body, requestBodyBuilder.listCurrentOrdersBody("9292"));
    }

    @Test
    void testPlaceOrdersBody() {
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
        long betId = 234234L;
        String marketId = "1.837454";

        String body = "{\"marketId\": \"" + marketId + "\"," +
                "\"instructions\": [{\"betId\": " + betId + "," +
                "\"sizeReduction\": null}]}";

        assertEquals(body, requestBodyBuilder.cancelOrdersBody(marketId, betId));
    }
}
