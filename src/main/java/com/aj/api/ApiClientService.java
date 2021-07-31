package com.aj.api;

import okhttp3.HttpUrl;

import java.io.IOException;

public interface ApiClientService {

    public String login(String username, String password) throws IOException;

    public String listEventTypes() throws IOException;
}
