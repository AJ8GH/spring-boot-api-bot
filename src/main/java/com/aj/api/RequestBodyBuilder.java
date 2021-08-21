package com.aj.api;

import com.aj.api.bettingTypes.MarketFilter;
import com.aj.api.bettingTypes.RequestBody;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@AllArgsConstructor
@Service
public class RequestBodyBuilder implements RequestBodyBuilderService {
    private final ObjectMapper objectMapper;

    private final String LIST_EVENT_TYPES_BODY = "{\"filter\":{}}";
    private final String LIST_EVENTS_BODY = "{\"filter\":{\"eventTypeIds\":[%s]}}";
    private final String LIST_ALL_CURRENT_ORDERS_BODY = "{\"orderProjection\": \"EXECUTABLE\"}";
    private final String LIST_CURRENT_ORDERS_BODY = "{\"orderProjection\": " +
            "\"EXECUTABLE\",\"betIds\": [\"%s\"]}";
    private final String LIST_MARKET_CATALOGUE_BODY =
            "{\"filter\":{\"%s\":[\"%s\"]},\"marketProjection\": " +
            "[\"RUNNER_DESCRIPTION\", \"COMPETITION\", \"EVENT\", " +
            "\"EVENT_TYPE\"],\"maxResults\":\"200\"}";
    private final String LIST_MARKET_BOOK_BODY =
            "{\"marketIds\": [\"%s\"],\"priceProjection\"" +
            ": {\"priceData\": [\"EX_BEST_OFFERS\", \"EX_TRADED\"]," +
            "\"virtualise\": \"true\"}}}";
    private final String PLACE_ORDERS_BODY =
            "{\"marketId\": \"%s\"," +
            "\"instructions\": [{\"selectionId\": %s" +
            ",\"side\": \"%s\",\"orderType\": \"LIMIT\"," +
            "\"limitOrder\": {\"size\": %s," +
            "\"price\": %s}}]}";
    private final String CANCEL_ORDERS_BODY = "{\"marketId\": \"%s\"," +
            "\"instructions\": [{\"betId\": %s," +
            "\"sizeReduction\": null}]}";

    @Override
    public String listEventTypesBody() {
        return LIST_EVENT_TYPES_BODY;
    }

    @Override
    public String listEventsBody(String eventTypeId) throws JsonProcessingException {
        MarketFilter filter = MarketFilter.builder()
                .eventTypeIds(Set.of(eventTypeId))
                .build();

        RequestBody requestBody = RequestBody.builder()
                .filter(filter)
                .build();

        return objectMapper.writeValueAsString(requestBody);
    }

    @Override
    public String listMarketCatalogueBody(String filter, String id) {
        return String.format(LIST_MARKET_CATALOGUE_BODY, filter, id);
    }

    @Override
    public String listMarketBookBody(String marketId) {
        return String.format(LIST_MARKET_BOOK_BODY, marketId);
    }

    @Override
    public String placeOrdersBody(String marketId, long selectionId,
                                  String side, double size, double price) {
        return String.format(PLACE_ORDERS_BODY,
                marketId, selectionId, side, size, price);
    }

    @Override
    public String cancelOrdersBody(String marketId, long betId) {
        return String.format(CANCEL_ORDERS_BODY, marketId, betId);
    }

    @Override
    public String listCurrentOrdersBody(String betId) {
        return String.format(LIST_CURRENT_ORDERS_BODY, betId);
    }

    @Override
    public String listCurrentOrdersBody() {
        return LIST_ALL_CURRENT_ORDERS_BODY;
    }
}
