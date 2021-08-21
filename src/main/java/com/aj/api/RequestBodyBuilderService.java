package com.aj.api;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface RequestBodyBuilderService {
    public String listEventTypesBody() throws JsonProcessingException;
    public String listEventsBody(String eventTypeId) throws JsonProcessingException;
    public String catalogueByEventIdBody(String eventId) throws JsonProcessingException;
    public String catalogueByMarketIdBody(String eventId) throws JsonProcessingException;
    public String listMarketBookBody(String marketId) throws JsonProcessingException;
    public String listCurrentOrdersBody() throws JsonProcessingException;
    public String listCurrentOrdersBody(String betId) throws JsonProcessingException;
    public String placeOrdersBody(String marketId, long selectionId,
                                  String side, double price, double size) throws JsonProcessingException;
    public String cancelOrdersBody(String marketId, String betId) throws JsonProcessingException;
}
