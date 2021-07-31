package com.aj.api;

import org.springframework.stereotype.Service;

@Service
public class RequestBodyBuilder implements RequestBodyBuilderService {

    private final String EVENT_TYPES_BODY = "{\"filter\":{}}";

    private final String EVENTS_BODY = "{\"filter\":{\"eventTypeIds\":[%s]}}";

    private final String MARKET_CATALOGUE_BODY =
            "{\"filter\":{\"eventIds\":[%s]},\"marketProjection\": " +
            "[\"RUNNER_DESCRIPTION\"],\"maxResults\":\"200\"}";

    private final String MARKET_BOOK_BODY =
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
    public String eventTypesBody() {
        return EVENT_TYPES_BODY;
    }
}
