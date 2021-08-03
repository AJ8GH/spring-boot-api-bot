package com.aj.api;

import com.aj.models.Bet;
import com.aj.models.UserSession;
import lombok.Builder;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ApiClient implements ApiClientService {

    private static UserSession userSession;

    private final UrlBuilder urlBuilder;
    private final RequestBodyBuilder requestBodyBuilder;
    private final OkHttpClient CLIENT = new OkHttpClient();

    private final String ACCEPT_HEADER = "Accept";
    private final String X_APPLICATION_HEADER = "X-Application";
    private final String X_APPLICATION = "testAccountApp";
    private final String X_AUTHENTICATION_HEADER = "X-Authentication";
    private final String CONTENT_TYPE_HEADER = "content-type";
    private final String CONTENT_TYPE = "application/json";
    private final String X_IP_HEADER = "X-IP";
    private final String X_IP = "127.0.0.1";

    public ApiClient(UrlBuilder urlBuilder,
                     RequestBodyBuilder requestBodyBuilder) {
        this.urlBuilder = urlBuilder;
        this.requestBodyBuilder = requestBodyBuilder;
    }

    public static void setUserSession(UserSession userSession) throws IOException {
        userSession.loadAppKey();
        ApiClient.userSession = userSession;
    }

    public static UserSession getUserSession() {
        return userSession;
    }

    @Override
    public String login(String username, String password) throws IOException {
        HttpUrl url = urlBuilder.createLoginUrl(username, password);
        Request request = createLoginRequest(url);
        return CLIENT.newCall(request).execute().body().string();
    }

    @Override
    public String listEventTypes() throws IOException {
        HttpUrl url = urlBuilder.createBettingUrl(urlBuilder.LIST_EVENT_TYPES);
        String body = requestBodyBuilder.listEventTypesBody();
        Request request = createBettingRequest(url, body);
        return CLIENT.newCall(request).execute().body().string();
    }

    @Override
    public String listEvents(long eventTypeId) throws IOException {
        HttpUrl url = urlBuilder.createBettingUrl(urlBuilder.LIST_EVENTS);
        String body = requestBodyBuilder.listEventsBody(eventTypeId);
        Request request = createBettingRequest(url, body);
        return CLIENT.newCall(request).execute().body().string();
    }

    @Override
    public String listMarketCatalogue(long eventId) throws IOException {
        HttpUrl url = urlBuilder.createBettingUrl(urlBuilder.LIST_MARKET_CATALOGUE);
        String body = requestBodyBuilder.listMarketCatalogueBody(eventId);
        Request request = createBettingRequest(url, body);
        return CLIENT.newCall(request).execute().body().string();
    }

    @Override
    public String listMarketBook(String marketId) throws IOException {
        HttpUrl url = urlBuilder.createBettingUrl(urlBuilder.LIST_MARKET_BOOK);
        String body = requestBodyBuilder.listMarketBookBody(marketId);
        Request request = createBettingRequest(url, body);
        return CLIENT.newCall(request).execute().body().string();
    }

    @Override
    public String listCurrentOrders() throws IOException {
        HttpUrl url = urlBuilder.createBettingUrl(urlBuilder.LIST_CURRENT_ORDERS);
        Request request = createBettingRequest(url, "");
        return CLIENT.newCall(request).execute().body().string();
    }

    @Override
    public String placeOrders(String marketId, long selectionId, String side,
                              double size, double price) throws IOException {
        HttpUrl url = urlBuilder.createBettingUrl(urlBuilder.PLACE_ORDERS);
        String body = requestBodyBuilder.placeOrdersBody(marketId, selectionId, side, size, price);
        Request request = createBettingRequest(url, body);
        return CLIENT.newCall(request).execute().body().string();
    }

    public String cancelOrders(String marketId, long betId) throws IOException {
        HttpUrl url = urlBuilder.createBettingUrl(urlBuilder.CANCEL_ORDERS);
        String body = requestBodyBuilder.cancelOrdersBody(marketId, betId);
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
