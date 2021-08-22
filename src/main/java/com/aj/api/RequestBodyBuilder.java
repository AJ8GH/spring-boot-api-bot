package com.aj.api;

import com.aj.api.RequestBodyBuilderService;
import com.aj.models.bettingTypes.*;
import com.aj.models.enumTypes.*;
import com.aj.models.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RequestBodyBuilder implements RequestBodyBuilderService {
    private final int DEFAULT_MAX_RESULTS = 200;
    private final boolean DEFAULT_VIRTUALISE = true;
    private final ObjectMapper mapper;

    public RequestBodyBuilder(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public String listEventTypesBody() throws JsonProcessingException {
        MarketFilter filter = MarketFilter.builder().build();
        RequestBody requestBody = RequestBody.builder()
                .filter(filter)
                .build();

        return mapper.writeValueAsString(requestBody);
    }

    @Override
    public String listEventsBody(String eventTypeId)
            throws JsonProcessingException {
        MarketFilter filter = MarketFilter.builder()
                .eventTypeIds(Set.of(eventTypeId))
                .build();

        RequestBody requestBody = RequestBody.builder()
                .filter(filter)
                .build();

        return mapper.writeValueAsString(requestBody);
    }

    @Override
    public String catalogueByEventIdBody(String eventId) throws JsonProcessingException {
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
                .maxResults(DEFAULT_MAX_RESULTS)
                .build();

        return mapper.writeValueAsString(requestBody);
    }

    @Override
    public String catalogueByMarketIdBody(String marketId) throws JsonProcessingException {
        Set<MarketProjection> marketProjection = Set.of(
                MarketProjection.EVENT,
                MarketProjection.EVENT_TYPE,
                MarketProjection.MARKET_DESCRIPTION,
                MarketProjection.COMPETITION,
                MarketProjection.RUNNER_DESCRIPTION);

        MarketFilter filter = MarketFilter.builder()
                .marketIds(Set.of(marketId))
                .build();

        RequestBody requestBody = RequestBody.builder()
                .filter(filter)
                .marketProjection(marketProjection)
                .maxResults(DEFAULT_MAX_RESULTS)
                .build();

        return mapper.writeValueAsString(requestBody);
    }

    @Override
    public String listMarketBookBody(String marketId) throws JsonProcessingException {
        PriceProjection priceProjection = PriceProjection.builder()
                .priceData(Set.of(PriceData.EX_BEST_OFFERS, PriceData.EX_TRADED))
                .virtualise(DEFAULT_VIRTUALISE)
                .build();

        RequestBody requestBody = RequestBody.builder()
                .marketIds(Set.of(marketId))
                .priceProjection(priceProjection)
                .build();

        return mapper.writeValueAsString(requestBody);
    }

    @Override
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

        return mapper.writeValueAsString(requestBody);
    }

    @Override
    public String cancelOrdersBody(String marketId, String betId) throws JsonProcessingException {
        CancelInstruction cancelInstruction = CancelInstruction.builder()
                .betId(betId)
                .build();

        RequestBody requestBody = RequestBody.builder()
                .marketId(marketId)
                .instructions(Set.of(cancelInstruction))
                .build();

        return mapper.writeValueAsString(requestBody);
    }

    @Override
    public String listCurrentOrdersBody(String betId) throws JsonProcessingException {
        RequestBody requestBody = RequestBody.builder()
                .betIds(Set.of(betId))
                .orderProjection(OrderProjection.EXECUTABLE)
                .build();

        return mapper.writeValueAsString(requestBody);
    }

    @Override
    public String listCurrentOrdersBody() throws JsonProcessingException {
        RequestBody requestBody = RequestBody.builder()
                .orderProjection(OrderProjection.EXECUTABLE)
                .orderBy(OrderBy.BY_PLACE_TIME)
                .sortDir(SortDir.LATEST_TO_EARLIEST)
                .build();

        return mapper.writeValueAsString(requestBody);
    }
}
