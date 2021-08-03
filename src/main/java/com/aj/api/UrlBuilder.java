package com.aj.api;

import okhttp3.HttpUrl;
import org.springframework.stereotype.Service;

@Service
public class UrlBuilder implements UrlBuilderService {
    public final String LOGIN_ENDPOINT = "http://identitysso.nxt.com.betfair/api/login";
    public final String BETTING_ENDPOINT="http://ang.nxt.internal/exchange/betting/rest/v1.0";

    public final String LIST_EVENT_TYPES = "/listEventTypes/";
    public final String LIST_EVENTS = "/listEvents/";
    public final String LIST_MARKET_CATALOGUE = "/listMarketCatalogue/";
    public final String LIST_MARKET_BOOK = "/listMarketBook/";
    public final String PLACE_ORDERS = "/placeOrders/";
    public final String LIST_CURRENT_ORDERS = "/listCurrentOrders/";
    public final String CANCEL_ORDERS = "/cancelOrders/";

    @Override
    public HttpUrl createLoginUrl(String username, String password) {
        String usernameParam = "username=" + username;
        String passwordParam = "password=" + password;
        String query = "?" + usernameParam + "&" + passwordParam;
        return HttpUrl.parse(LOGIN_ENDPOINT + query);
    }

    @Override
    public HttpUrl createBettingUrl(String operation) {
        return HttpUrl.parse(BETTING_ENDPOINT + operation);
    }
}
