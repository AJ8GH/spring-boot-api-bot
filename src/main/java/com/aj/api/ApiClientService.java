package com.aj.api;

import okhttp3.HttpUrl;

import java.io.IOException;

public interface ApiClientService {
    public String loginCall(HttpUrl url) throws Exception;
    public String bettingCall(HttpUrl url, String body) throws IOException;
}
