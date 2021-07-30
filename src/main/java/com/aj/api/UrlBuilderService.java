package com.aj.api;

import okhttp3.HttpUrl;

public interface UrlBuilderService {
    public final String LOGIN_ENDPOINT = "http://identitysso.nxt.com.betfair/api/login";
    public final String BETTING_ENDPOINT="http://ang.nxt.internal/exchange/betting/rest/v1.0";

    public final String LIST_EVENT_TYPES = "/listEventTypes/";
    public final String LIST_EVENTS = "/listEvents/";
    public final String LIST_MARKET_CATALOGUE = "/listMarketCatalogue/";
    public final String LIST_MARKET_BOOK = "/listMarketBook/";
    public final String PLACE_ORDERS = "/placeOrders/";
    public final String LIST_CURRENT_ORDERS = "/listCurrentOrders/";
    public final String CANCEL_ORDERS = "/cancelOrders/";

    public HttpUrl createLoginUrl(String username, String password);
    public HttpUrl createBettingUrl(String operation);
}
