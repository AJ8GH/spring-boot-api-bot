package com.aj.api;

public interface RequestBodyBuilderService {
    public String listEventTypesBody();
    public String listEventsBody(long eventTypeId);
    public String listMarketCatalogueBody(long eventId);
    public String listMarketBookBody(String marketId);
}
