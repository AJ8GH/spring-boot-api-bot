package com.aj.api;

import com.aj.domain.bettingtypes.UserSession;
import com.fasterxml.jackson.core.JsonProcessingException;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ApiClient implements ApiClientService {
    private UserSession userSession;
    private final Logger LOGGER = LoggerFactory.getLogger(ApiClient.class);
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

    public void setUserSession(UserSession userSession) throws IOException {
        userSession.loadAppKey();
        this.userSession = userSession;
    }

    public UserSession getUserSession() {
        return userSession;
    }

    @Override
    public String login(String username, String password) {
        HttpUrl url = urlBuilder.createLoginUrl(username, password);
        return createRequestAndMakeLoginCall(url);
    }

    @Override
    public String listEventTypes() throws JsonProcessingException {
        HttpUrl url = urlBuilder.createBettingUrl(urlBuilder.LIST_EVENT_TYPES);
        String body = requestBodyBuilder.listEventTypesBody();
        return createRequestAndMakeCall(url, body);
    }

    @Override
    public String catalogueByMarket(String marketId) throws JsonProcessingException {
        HttpUrl url = urlBuilder.createBettingUrl(urlBuilder.LIST_MARKET_CATALOGUE);
        String body = requestBodyBuilder.catalogueByMarketIdBody(marketId);
        return createRequestAndMakeCall(url, body);
    }

    @Override
    public String catalogueByEvent(String eventId) throws JsonProcessingException {
        HttpUrl url = urlBuilder.createBettingUrl(urlBuilder.LIST_MARKET_CATALOGUE);
        String body = requestBodyBuilder.catalogueByEventIdBody(eventId);
        return createRequestAndMakeCall(url, body);
    }

    @Override
    public String listEvents(String eventTypeId) throws JsonProcessingException {
        HttpUrl url = urlBuilder.createBettingUrl(urlBuilder.LIST_EVENTS);
        String body = requestBodyBuilder.listEventsBody(eventTypeId);
        return createRequestAndMakeCall(url, body);
    }

    @Override
    public String listMarketBook(String marketId) throws JsonProcessingException {
        HttpUrl url = urlBuilder.createBettingUrl(urlBuilder.LIST_MARKET_BOOK);
        String body = requestBodyBuilder.listMarketBookBody(marketId);
        return createRequestAndMakeCall(url, body);
    }

    @Override
    public String listCurrentOrders(String betId) throws JsonProcessingException {
        HttpUrl url = urlBuilder.createBettingUrl(urlBuilder.LIST_CURRENT_ORDERS);
        String body = requestBodyBuilder.listCurrentOrdersBody(betId);
        return createRequestAndMakeCall(url, body);
    }

    @Override
    public String listCurrentOrders() throws JsonProcessingException {
        HttpUrl url = urlBuilder.createBettingUrl(urlBuilder.LIST_CURRENT_ORDERS);
        String body = requestBodyBuilder.listCurrentOrdersBody();
        return createRequestAndMakeCall(url, body);
    }

    @Override
    public String placeOrders(String marketId, long selectionId, String side,
                              double size, double price) throws JsonProcessingException {
        HttpUrl url = urlBuilder.createBettingUrl(urlBuilder.PLACE_ORDERS);
        String body = requestBodyBuilder.placeOrdersBody(marketId, selectionId, side, size, price);
        return createRequestAndMakeCall(url, body);
    }

    @Override
    public String cancelOrders(String marketId, String betId) throws JsonProcessingException {
        HttpUrl url = urlBuilder.createBettingUrl(urlBuilder.CANCEL_ORDERS);
        String body = requestBodyBuilder.cancelOrdersBody(marketId, betId);
        return createRequestAndMakeCall(url, body);
    }

    private String createRequestAndMakeCall(HttpUrl url, String body) {
        Request request = createBettingRequest(url, body);
        return makeCall(request);
    }

    private String createRequestAndMakeLoginCall(HttpUrl url) {
        Request request = createLoginRequest(url);
        return makeCall(request);
    }

    private String makeCall(Request request) {
        try {
            Response response = CLIENT.newCall(request).execute();
            String body = response.body().string();
            LOGGER.info("Response: {}", response);
            LOGGER.info("Body: {}", body);
            return body;

        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        return null;
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
