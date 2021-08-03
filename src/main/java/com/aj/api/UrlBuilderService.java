package com.aj.api;

import okhttp3.HttpUrl;

public interface UrlBuilderService {
    public HttpUrl createLoginUrl(String username, String password);
    public HttpUrl createBettingUrl(String operation);
}
