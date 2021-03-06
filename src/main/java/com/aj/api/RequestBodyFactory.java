package com.aj.api;

import com.aj.domain.bettingenums.*;
import com.aj.domain.bettingtypes.*;
import com.aj.domain.bettingtypes.RequestBody.RequestBodyBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RequestBodyFactory {
    private final int DEFAULT_MAX_RESULTS = 200;
    private final boolean DEFAULT_VIRTUALISE = true;
    private final ObjectMapper mapper;

    public RequestBodyFactory(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public String listEventTypesBody() throws JsonProcessingException {
        MarketFilter filter = MarketFilter.builder().build();
        RequestBody requestBody = RequestBody.builder()
                .filter(filter)
                .build();

        return serialise(requestBody);
    }

    public String listEventsBody(String eventTypeId)
            throws JsonProcessingException {
        MarketFilter filter = MarketFilter.builder()
                .eventTypeIds(Set.of(eventTypeId))
                .build();

        RequestBody requestBody = RequestBody.builder()
                .filter(filter)
                .build();

        System.out.println(serialise(requestBody));
        return serialise(requestBody);
    }

    public String catalogueByEventIdBody(String eventId) throws JsonProcessingException {
        MarketFilter filter = MarketFilter.builder()
                .eventIds(Set.of(eventId))
                .build();
        RequestBody requestBody = createCatalogueRequestBuilder()
                .filter(filter)
                .build();

        return serialise(requestBody);
    }

    public String catalogueByMarketIdBody(String marketId) throws JsonProcessingException {
        MarketFilter filter = MarketFilter.builder()
                .marketIds(Set.of(marketId))
                .build();
        RequestBody requestBody = createCatalogueRequestBuilder()
                .filter(filter)
                .build();

        return serialise(requestBody);
    }

    public String listMarketBookBody(String marketId) throws JsonProcessingException {
        PriceProjection priceProjection = PriceProjection.builder()
                .priceData(Set.of(PriceData.EX_BEST_OFFERS, PriceData.EX_TRADED))
                .virtualise(DEFAULT_VIRTUALISE)
                .build();
        RequestBody requestBody = RequestBody.builder()
                .marketIds(Set.of(marketId))
                .priceProjection(priceProjection)
                .build();

        return serialise(requestBody);
    }

    public String placeOrdersBody(String marketId, long selectionId,
                                  String side, double size, double price) throws JsonProcessingException {

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

        return serialise(requestBody);
    }

    public String cancelOrdersBody(String marketId, String betId) throws JsonProcessingException {
        CancelInstruction cancelInstruction = CancelInstruction.builder()
                .betId(betId)
                .build();
        RequestBody requestBody = RequestBody.builder()
                .marketId(marketId)
                .instructions(Set.of(cancelInstruction))
                .build();

        return serialise(requestBody);
    }

    public String listCurrentOrdersBody(String betId) throws JsonProcessingException {
        RequestBody requestBody = RequestBody.builder()
                .betIds(Set.of(betId))
                .orderProjection(OrderProjection.EXECUTABLE)
                .build();

        return serialise(requestBody);
    }

    public String listCurrentOrdersBody() throws JsonProcessingException {
        RequestBody requestBody = RequestBody.builder()
                .orderProjection(OrderProjection.EXECUTABLE)
                .orderBy(OrderBy.BY_PLACE_TIME)
                .sortDir(SortDir.LATEST_TO_EARLIEST)
                .build();

        return serialise(requestBody);
    }

    private RequestBodyBuilder createCatalogueRequestBuilder() {
        Set<MarketProjection> marketProjection = Set.of(
                MarketProjection.EVENT,
                MarketProjection.EVENT_TYPE,
                MarketProjection.MARKET_DESCRIPTION,
                MarketProjection.COMPETITION,
                MarketProjection.RUNNER_DESCRIPTION);

        return RequestBody.builder()
                .marketProjection(marketProjection)
                .maxResults(DEFAULT_MAX_RESULTS);
    }

    private String serialise(RequestBody payload)
            throws JsonProcessingException {
        return mapper.writeValueAsString(payload);
    }
}
