package com.aj.api;

public interface RequestBodyBuilderService {
    public String listEventTypesBody();
    public String listEventsBody(long eventTypeId);
    public String listMarketCatalogueBody(long eventId);
    public String listMarketBookBody(String marketId);
    public String listCurrentOrdersBody();
    public String placeOrdersBody(String marketId, long selectionId,
                                  String side, double price, double size);
    public String cancelOrdersBody(String marketId, long betId);
}
