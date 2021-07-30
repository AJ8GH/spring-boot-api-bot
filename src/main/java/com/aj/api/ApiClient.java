package com.aj.api;

import com.aj.BetfairApiBot1Application;
import com.aj.models.UserSession;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Service
public class ApiClient implements ApiClientService {

    private static UserSession userSession;
    private final String ACCEPT_HEADER = "Accept";
    private final String X_APPLICATION_HEADER = "X-Application";
    private final String X_APPLICATION = "testAccountApp";
    private final String X_AUTHENTICATION_HEADER = "X-Authentication";
    private final String CONTENT_TYPE_HEADER = "content-type";
    private final String CONTENT_TYPE = "application/json";
    private final String X_IP_HEADER = "X-IP";
    private final String X_IP = "127.0.0.1";
    private final OkHttpClient CLIENT = new OkHttpClient();

    public static void setUserSession(UserSession userSession) {
        ApiClient.userSession = userSession;
    }

    public static UserSession getUserSession() {
        return userSession;
    }

    @Override
    public String loginCall(HttpUrl url) throws IOException {
        Request request = createLoginRequest(url);
        return CLIENT.newCall(request).execute().body().string();
    }

    @Override
    public String bettingCall(HttpUrl url, String body) throws IOException {
        Request request = createBettingRequest(url, body);
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

    private Request createBettingRequest(HttpUrl url, String body) {
        MediaType mediaType = MediaType.parse(CONTENT_TYPE);
        RequestBody requestBody = RequestBody.create(body, mediaType);
        return new Request.Builder()
                .url(url)
                .post(requestBody)
                .addHeader(ACCEPT_HEADER, CONTENT_TYPE)
                .addHeader(X_AUTHENTICATION_HEADER, userSession.getToken())
                .addHeader(X_APPLICATION_HEADER, userSession.getAppKey())
                .addHeader(CONTENT_TYPE_HEADER, CONTENT_TYPE)
                .addHeader(X_IP_HEADER, X_IP)
                .build();
    }
}
