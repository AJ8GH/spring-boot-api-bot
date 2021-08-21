package com.aj.api;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface RequestBodyBuilderService {
    public String listEventTypesBody();
    public String listEventsBody(String eventTypeId) throws JsonProcessingException;
    public String listMarketCatalogueBody(String filter, String id);
    public String listMarketBookBody(String marketId);
    public String listCurrentOrdersBody();
    public String listCurrentOrdersBody(String betId);
    public String placeOrdersBody(String marketId, long selectionId,
                                  String side, double price, double size);
    public String cancelOrdersBody(String marketId, long betId);
}
