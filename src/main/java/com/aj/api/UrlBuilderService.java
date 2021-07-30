package com.aj.api;

import okhttp3.HttpUrl;

public interface UrlBuilderService {
    public final String LOGIN_ENDPOINT = "http://identitysso.nxt.com.betfair/api/login";
    public final String EVENT_TYPES_OPERATION = "/listEventTypes/";
    public final String EVENTS_OPERATION = "/listEvents/";
    public final String MARKET_OPERATION = "/listMarketCatalogue/";
    public final String PRICE_OPERATION = "/listMarketBook/";
    public final String PLACE_ORDERS_OPERATION = "/placeOrders/";
    public final String LIST_ORDERS_OPERATION = "/listCurrentOrders/";
    public final String CANCEL_ORDERS_OPERATION = "/cancelOrders/";

    public HttpUrl createLoginUrl(String username, String password);
}
