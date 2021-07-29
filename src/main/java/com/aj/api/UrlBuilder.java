package com.aj.api;

import okhttp3.HttpUrl;
import org.springframework.stereotype.Component;

public class UrlBuilder {

    private final String LOGIN_ENDPOINT = "http://identitysso.nxt.com.betfair/api/login";
    public final String EVENT_TYPES_OPERATION = "/listEventTypes/";
    public final String EVENTS_OPERATION = "/listEvents/";
    public final String MARKET_OPERATION = "/listMarketCatalogue/";
    public final String PRICE_OPERATION = "/listMarketBook/";
    public final String PLACE_ORDERS_OPERATION = "/placeOrders/";
    public final String LIST_ORDERS_OPERATION = "/listCurrentOrders/";
    public final String CANCEL_ORDERS_OPERATION = "/cancelOrders/";

    public HttpUrl createLoginUrl(String username, String password) {
        String usernameParam = "username=" + username;
        String passwordParam = "password=" + password;
        String query = "?" + usernameParam + "&" + passwordParam;
        return HttpUrl.parse(LOGIN_ENDPOINT + query);
    }
}
