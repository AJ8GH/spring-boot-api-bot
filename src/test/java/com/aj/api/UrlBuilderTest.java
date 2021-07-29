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
}
