package com.aj.api;

import okhttp3.*;
import org.springframework.stereotype.Component;

import javax.websocket.Session;
import java.io.IOException;

public class ApiClient {

    private final String ACCEPT_HEADER = "Accept";
    private final String X_APPLICATION_HEADER = "X-Application";
    private final String X_APPLICATION = "testAccountApp";
    private final String X_AUTHENTICATION_HEADER = "X-Authentication";
    private final String CONTENT_TYPE_HEADER = "content-type";
    private final String CONTENT_TYPE = "application/json";
    private final String X_IP_HEADER = "X-IP";
    private final String X_IP = "127.0.0.1";
    private final OkHttpClient CLIENT = new OkHttpClient();

    private Session session;

    public void setSession(Session session) {
        this.session = session;
    }

    public String loginCall(HttpUrl url) throws IOException {
        Request request = createLoginRequest(url);
        return CLIENT.newCall(request).execute().body().string();
    }

    private Request createLoginRequest(HttpUrl url) {
        return new Request.Builder()
                .url(url)
                .post(RequestBody.create("", MediaType.parse("")))
                .addHeader(ACCEPT_HEADER, CONTENT_TYPE)
                .addHeader(X_APPLICATION_HEADER, X_APPLICATION)
                .addHeader(CONTENT_TYPE_HEADER, CONTENT_TYPE)
                .addHeader(X_IP_HEADER, X_IP)
                .build();
    }
}
