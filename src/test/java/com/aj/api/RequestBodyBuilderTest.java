package com.aj.api;

import com.aj.api.bettingTypes.*;
import com.aj.api.enumTypes.*;
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
    void testListEventTypesBody() throws JsonProcessingException {
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
    void testListMarketCatalogueBody() throws JsonProcessingException {
        String eventId = "999";
        Set<MarketProjection> marketProjection = Set.of(
                MarketProjection.EVENT,
                MarketProjection.EVENT_TYPE,
                MarketProjection.MARKET_DESCRIPTION,
                MarketProjection.COMPETITION,
                MarketProjection.RUNNER_DESCRIPTION);

        MarketFilter filter = MarketFilter.builder()
                .eventIds(Set.of(eventId))
                .build();

        RequestBody requestBody = RequestBody.builder()
                .filter(filter)
                .marketProjection(marketProjection)
                .maxResults(200)
                .build();

        String body = mapper.writeValueAsString(requestBody);

        assertEquals(body, requestBodyBuilder.listMarketCatalogueBody(eventId));
    }

    @Test
    void testListMarketBookBody() throws JsonProcessingException {
        String marketId = "1.23456789";

        PriceProjection priceProjection = PriceProjection.builder()
                .priceData(Set.of(PriceData.EX_BEST_OFFERS, PriceData.EX_TRADED))
                .virtualise(true)
                .build();

        RequestBody requestBody = RequestBody.builder()
                .marketIds(Set.of(marketId))
                .priceProjection(priceProjection)
                .build();

        String body = mapper.writeValueAsString(requestBody);

        assertEquals(body, requestBodyBuilder.listMarketBookBody(marketId));
    }

    @Test
    void testListCurrentOrdersBody() throws JsonProcessingException {
        String body = "{\"orderProjection\":\"EXECUTABLE\"}";

        assertEquals(body, requestBodyBuilder.listCurrentOrdersBody());
    }

    @Test
    void testListCurrentOrdersBodyWithBetId() throws JsonProcessingException {
        String betId = "9292";
        RequestBody requestBody = RequestBody.builder()
                .betIds(Set.of(betId))
                .orderProjection(OrderProjection.EXECUTABLE)
                .build();

        String body = mapper.writeValueAsString(requestBody);

        assertEquals(body, requestBodyBuilder.listCurrentOrdersBody(betId));
    }

    @Test
    void testPlaceOrdersBody() throws JsonProcessingException {
        String marketId = "1.23456789";
        long selectionId = 2345L;
        String side = "BACK";
        double price = 5.0;
        double size = 7.0;

        LimitOrder limitOrder = LimitOrder.builder()
                .price(price)
                .size(size)
                .build();

        PlaceInstruction placeInstruction = PlaceInstruction.builder()
                .selectionId(selectionId)
                .side(Side.valueOf(side))
                .orderType(OrderType.LIMIT)
                .limitOrder(limitOrder)
                .build();

        RequestBody requestBody = RequestBody.builder()
                .marketId(marketId)
                .instructions(Set.of(placeInstruction))
                .build();

        String body = mapper.writeValueAsString(requestBody);

        assertEquals(body, requestBodyBuilder.placeOrdersBody(
                marketId, selectionId, side, size, price));
    }

    @Test
    void testCancelOrdersBody() throws JsonProcessingException {
        String betId = "234234";
        String marketId = "1.837454";

        CancelInstruction cancelInstruction = CancelInstruction.builder()
                .betId(betId)
                .build();

        RequestBody requestBody = RequestBody.builder()
                .marketId(marketId)
                .instructions(Set.of(cancelInstruction))
                .build();

        String body = mapper.writeValueAsString(requestBody);

        assertEquals(body, requestBodyBuilder.cancelOrdersBody(marketId, betId));
    }
}
