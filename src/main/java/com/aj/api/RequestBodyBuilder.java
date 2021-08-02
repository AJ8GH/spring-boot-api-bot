package com.aj.api;

import org.springframework.stereotype.Service;

@Service
public class RequestBodyBuilder implements RequestBodyBuilderService {

    private final String LIST_EVENT_TYPES_BODY = "{\"filter\":{}}";

    private final String LIST_EVENTS_BODY = "{\"filter\":{\"eventTypeIds\":[%s]}}";

    private final String LIST_MARKET_CATALOGUE_BODY =
            "{\"filter\":{\"eventIds\":[%s]},\"marketProjection\": " +
            "[\"RUNNER_DESCRIPTION\"],\"maxResults\":\"200\"}";

    private final String LIST_MARKET_BOOK_BODY =
            "{\"marketIds\": [%s],\"priceProjection\"" +
            ": {\"priceData\": [\"EX_BEST_OFFERS\", \"EX_TRADED\"]," +
            "\"virtualise\": \"true\"}}}";

    private final String PLACE_ORDERS_BODY =
            "{\"marketId\": \"%s\"," +
            "\"instructions\": [{\"selectionId\": \"%s" +
            "\",\"side\": \"%s\",\"orderType\": \"LIMIT\"," +
            "\"limitOrder\": {\"size\": \"%s\"," +
            "\"price\": \"%s\"}}]}";

    private final String CANCEL_ORDERS_BODY = "{\n" +
            "    \"marketId\": \"%s\",\n" +
            "    \"instructions\":\n" +
            "        [\n" +
            "            {\n" +
            "                \"betId\": \"%s\"\n" +
            "            }\n" +
            "        ]\n" +
            "}";

    @Override
    public String listEventTypesBody() {
        return LIST_EVENT_TYPES_BODY;
    }

    @Override
    public String listEventsBody(long eventTypeId) {
        return String.format(LIST_EVENTS_BODY, eventTypeId);
    }

    @Override
    public String listMarketCatalogueBody(long eventId) {
        return String.format(LIST_MARKET_CATALOGUE_BODY, eventId);
    }

    @Override
    public String listMarketBookBody(String marketId) {
        return String.format(LIST_MARKET_BOOK_BODY, marketId);
    }
}
