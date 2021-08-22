package com.aj.enrichment;

public interface EnrichableMarket {
    String getMarketId();
    void setMarketName(String marketName);
    void setEventName(String eventName);
    void setEventTypeName(String eventTypeName);
    void setCompetitionName(String competitionName);
}
