package com.aj.api;

import okhttp3.HttpUrl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UrlBuilderTest {

    @Test
    void itReturnsCorrectLoginUrlWithUsernameAndPassword() {
        UrlBuilder urlBuilder = new UrlBuilder();

        String LOGIN_ENDPOINT = "http://identitysso.nxt.com.betfair/api/login";
        String query = "?username=username&password=password";
        HttpUrl expectedUrl = HttpUrl.parse(LOGIN_ENDPOINT + query);

        HttpUrl url = urlBuilder.createLoginUrl("username", "password");

        assertEquals(expectedUrl, url);
    }

    @Test
    void itReturnsTheBettingUrlWithCorrectOperation() {
        UrlBuilder urlBuilder = new UrlBuilder();

        String BETTING_ENDPOINT="http://ang.nxt.internal/exchange/betting/rest/v1.0";
        String operation = "/listEventTypes/";
        HttpUrl url = urlBuilder.createBettingUrl(operation);

        HttpUrl expectedUrl = HttpUrl.parse(BETTING_ENDPOINT + operation);

        assertEquals(expectedUrl, url);
    }
}
