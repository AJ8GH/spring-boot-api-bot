package com.aj.api;

import okhttp3.HttpUrl;

public interface ApiClientService {
    public String loginCall(HttpUrl url) throws Exception;
}
