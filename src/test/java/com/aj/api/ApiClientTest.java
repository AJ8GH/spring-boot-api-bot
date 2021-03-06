package com.aj.api;

import com.aj.domain.bettingtypes.UserSession;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ApiClientTest {
    private final String ACCEPT_HEADER = "Accept";
    private final String ACCEPT_VALUE = "application/json";
    private final String X_AUTHENTICATION_HEADER = "X-Authentication";
    private final String X_APPLICATION_HEADER = "X-Application";
    private final String X_APPLICATION_VALUE = "testAccountApp";
    private final String CONTENT_TYPE_HEADER = "Content-Type";
    private final String CONTENT_TYPE_VALUE = "application/json";
    private final String X_IP_HEADER = "X-IP";
    private final String X_IP_VALUE = "127.0.0.1";

    private final String APP_KEY = "APP_KEY";
    private final String TOKEN = "TOKEN";

    private MockWebServer server;
    private ApiClient apiClient;
    private UrlBuilder urlBuilder;
    private RequestBodyFactory requestBodyFactory;

    @BeforeEach
    public void setUp() throws IOException {
        urlBuilder = mock(UrlBuilder.class);
        requestBodyFactory = mock(RequestBodyFactory.class);
        apiClient = new ApiClient(urlBuilder, requestBodyFactory);

        UserSession userSession = mock(UserSession.class);
        when(userSession.getToken()).thenReturn(TOKEN);
        when(userSession.getAppKey()).thenReturn(APP_KEY);

        apiClient.setUserSession(userSession);

        server = new MockWebServer();
        server.start();
    }

    @AfterEach
    public void tearDown() throws IOException {
        server.shutdown();
    }

    @Test
    void getUserSession() throws IOException {
        UserSession userSession = mock(UserSession.class);
        apiClient.setUserSession(userSession);
        assertEquals(userSession, apiClient.getUserSession());
    }

    @Test
    void testLogin() throws Exception {
        String mockResponse = "{login response}";
        server.enqueue(new MockResponse().setBody(mockResponse));
        HttpUrl baseUrl = server.url("?username=username&password=password");

        when(urlBuilder.createLoginUrl("username", "password"))
                .thenReturn(baseUrl);

        String response = apiClient.login("username", "password");
        RecordedRequest request = server.takeRequest();

        assertEquals(mockResponse, response);
        assertEquals(baseUrl, request.getRequestUrl());
        assertEquals(X_APPLICATION_VALUE, request.getHeader(X_APPLICATION_HEADER));
        assertEquals(CONTENT_TYPE_VALUE, request.getHeader(CONTENT_TYPE_HEADER));
        assertEquals(ACCEPT_VALUE, request.getHeader(ACCEPT_HEADER));
        assertEquals(X_IP_VALUE, request.getHeader(X_IP_HEADER));
    }

    @Test
    void testListEventTypes() throws Exception {
        String mockResponse = "{listEventTypes response}";
        server.enqueue(new MockResponse().setBody(mockResponse));
        HttpUrl baseUrl = server.url("/listEventTypes");

        when(urlBuilder.createBettingUrl(urlBuilder.LIST_EVENT_TYPES)).thenReturn(baseUrl);
        when(requestBodyFactory.listEventTypesBody()).thenReturn("{listEventTypes body}");

        String response = apiClient.listEventTypes();
        RecordedRequest request = server.takeRequest();

        assertEquals(mockResponse, response);
        assertEquals(baseUrl, request.getRequestUrl());
        assertTrue(request.getBody().toString().contains("{listEventTypes body}"));
        assertEquals(APP_KEY, request.getHeader(X_APPLICATION_HEADER));
        assertEquals(TOKEN, request.getHeader(X_AUTHENTICATION_HEADER));
        assertTrue(request.getHeader(CONTENT_TYPE_HEADER).contains(CONTENT_TYPE_VALUE));
        assertEquals(ACCEPT_VALUE, request.getHeader(ACCEPT_HEADER));
        assertEquals(X_IP_VALUE, request.getHeader(X_IP_HEADER));
    }

    @Test
    void testListCurrentOrders() throws Exception {
        String mockResponse = "{listCurrentOrders response}";
        server.enqueue(new MockResponse().setBody(mockResponse));
        HttpUrl baseUrl = server.url("/listCurrentOrders");

        when(urlBuilder.createBettingUrl(urlBuilder.LIST_CURRENT_ORDERS)).thenReturn(baseUrl);
        when(requestBodyFactory.listCurrentOrdersBody()).thenReturn("{listCurrentOrders body}");

        String response = apiClient.listCurrentOrders();
        RecordedRequest request = server.takeRequest();

        verify(requestBodyFactory).listCurrentOrdersBody();
        verify(urlBuilder).createBettingUrl(urlBuilder.LIST_CURRENT_ORDERS);

        assertEquals(mockResponse, response);
        assertEquals(baseUrl, request.getRequestUrl());
        assertEquals(APP_KEY, request.getHeader(X_APPLICATION_HEADER));
        assertEquals(TOKEN, request.getHeader(X_AUTHENTICATION_HEADER));
        assertTrue(request.getBody().toString().contains("{listCurrentOrders body}"));
        assertTrue(request.getHeader(CONTENT_TYPE_HEADER).contains(CONTENT_TYPE_VALUE));
        assertEquals(ACCEPT_VALUE, request.getHeader(ACCEPT_HEADER));
        assertEquals(X_IP_VALUE, request.getHeader(X_IP_HEADER));
    }

    @Test
    void testListCurrentOrdersWithBetId() throws Exception {
        String mockResponse = "{listCurrentOrders response}";
        server.enqueue(new MockResponse().setBody(mockResponse));
        HttpUrl baseUrl = server.url("/listCurrentOrders");

        when(urlBuilder.createBettingUrl(urlBuilder.LIST_CURRENT_ORDERS)).thenReturn(baseUrl);
        when(requestBodyFactory.listCurrentOrdersBody("123456")).thenReturn("{listCurrentOrders body 123456}");

        String response = apiClient.listCurrentOrders("123456");
        RecordedRequest request = server.takeRequest();

        verify(requestBodyFactory).listCurrentOrdersBody("123456");
        verify(urlBuilder).createBettingUrl(urlBuilder.LIST_CURRENT_ORDERS);

        assertEquals(mockResponse, response);
        assertEquals(baseUrl, request.getRequestUrl());
        assertEquals(APP_KEY, request.getHeader(X_APPLICATION_HEADER));
        assertEquals(TOKEN, request.getHeader(X_AUTHENTICATION_HEADER));
        assertTrue(request.getBody().toString().contains("{listCurrentOrders body 123456}"));
        assertTrue(request.getHeader(CONTENT_TYPE_HEADER).contains(CONTENT_TYPE_VALUE));
        assertEquals(ACCEPT_VALUE, request.getHeader(ACCEPT_HEADER));
        assertEquals(X_IP_VALUE, request.getHeader(X_IP_HEADER));
    }

    @Test
    void testListEvents() throws Exception {
        String mockResponse = "{listEvents response}";
        server.enqueue(new MockResponse().setBody(mockResponse));
        HttpUrl baseUrl = server.url("/listEvents");

        when(urlBuilder.createBettingUrl(urlBuilder.LIST_EVENTS)).thenReturn(baseUrl);
        when(requestBodyFactory.listEventsBody("1")).thenReturn("{listEvents body}");

        String response = apiClient.listEvents("1");
        RecordedRequest request = server.takeRequest();

        verify(requestBodyFactory).listEventsBody("1");
        assertEquals(mockResponse, response);
        assertEquals(baseUrl, request.getRequestUrl());
        assertEquals(APP_KEY, request.getHeader(X_APPLICATION_HEADER));
        assertEquals(TOKEN, request.getHeader(X_AUTHENTICATION_HEADER));
        assertTrue(request.getBody().toString().contains("{listEvents body}"));
        assertTrue(request.getHeader(CONTENT_TYPE_HEADER).contains(CONTENT_TYPE_VALUE));
        assertEquals(ACCEPT_VALUE, request.getHeader(ACCEPT_HEADER));
        assertEquals(X_IP_VALUE, request.getHeader(X_IP_HEADER));
    }

    @Test
    void testListMarketCatalogue() throws Exception {
        String mockResponse = "{list market catalogue response}";
        server.enqueue(new MockResponse().setBody(mockResponse));
        HttpUrl baseUrl = server.url("/listMarketCatalogue");

        when(urlBuilder.createBettingUrl(urlBuilder.LIST_MARKET_CATALOGUE)).thenReturn(baseUrl);
        when(requestBodyFactory.catalogueByEventIdBody("999")).thenReturn("{listMarketCatalogue body}");

        String response = apiClient.catalogueByEvent("999");
        RecordedRequest request = server.takeRequest();

        verify(requestBodyFactory).catalogueByEventIdBody("999");
        verify(urlBuilder).createBettingUrl(urlBuilder.LIST_MARKET_CATALOGUE);

        assertEquals(mockResponse, response);
        assertEquals(baseUrl, request.getRequestUrl());
        assertEquals(APP_KEY, request.getHeader(X_APPLICATION_HEADER));
        assertEquals(TOKEN, request.getHeader(X_AUTHENTICATION_HEADER));
        assertTrue(request.getBody().toString().contains("{listMarketCatalogue body}"));
        assertTrue(request.getHeader(CONTENT_TYPE_HEADER).contains(CONTENT_TYPE_VALUE));
        assertEquals(ACCEPT_VALUE, request.getHeader(ACCEPT_HEADER));
        assertEquals(X_IP_VALUE, request.getHeader(X_IP_HEADER));
    }

    @Test
    void testCatalogueByMarket() throws Exception {
        String mockResponse = "{list market catalogue response}";
        server.enqueue(new MockResponse().setBody(mockResponse));
        HttpUrl baseUrl = server.url("/listMarketCatalogue");

        when(urlBuilder.createBettingUrl(urlBuilder.LIST_MARKET_CATALOGUE)).thenReturn(baseUrl);
        when(requestBodyFactory.catalogueByMarketIdBody("1.23")).thenReturn("{listMarketCatalogue body}");

        String response = apiClient.catalogueByMarket("1.23");
        RecordedRequest request = server.takeRequest();

        verify(requestBodyFactory).catalogueByMarketIdBody("1.23");
        verify(urlBuilder).createBettingUrl(urlBuilder.LIST_MARKET_CATALOGUE);

        assertEquals(mockResponse, response);
        assertEquals(baseUrl, request.getRequestUrl());
        assertEquals(APP_KEY, request.getHeader(X_APPLICATION_HEADER));
        assertEquals(TOKEN, request.getHeader(X_AUTHENTICATION_HEADER));
        assertTrue(request.getBody().toString().contains("{listMarketCatalogue body}"));
        assertTrue(request.getHeader(CONTENT_TYPE_HEADER).contains(CONTENT_TYPE_VALUE));
        assertEquals(ACCEPT_VALUE, request.getHeader(ACCEPT_HEADER));
        assertEquals(X_IP_VALUE, request.getHeader(X_IP_HEADER));
    }

    @Test
    void testListMarketBook() throws Exception {
        server.enqueue(new MockResponse().setBody("{listMarketBook response}"));
        HttpUrl baseUrl = server.url("/listMarketBook");

        when(urlBuilder.createBettingUrl(urlBuilder.LIST_MARKET_BOOK)).thenReturn(baseUrl);
        when(requestBodyFactory.listMarketBookBody("1.23456789")).thenReturn("{listMarketBook body}");

        String response = apiClient.listMarketBook("1.23456789");
        RecordedRequest request = server.takeRequest();

        verify(requestBodyFactory).listMarketBookBody("1.23456789");
        verify(urlBuilder).createBettingUrl(urlBuilder.LIST_MARKET_BOOK);

        assertEquals("{listMarketBook response}", response);
        assertEquals(baseUrl, request.getRequestUrl());
        assertEquals(APP_KEY, request.getHeader(X_APPLICATION_HEADER));
        assertEquals(TOKEN, request.getHeader(X_AUTHENTICATION_HEADER));
        assertTrue(request.getBody().toString().contains("{listMarketBook body}"));
        assertTrue(request.getHeader(CONTENT_TYPE_HEADER).contains(CONTENT_TYPE_VALUE));
        assertEquals(ACCEPT_VALUE, request.getHeader(ACCEPT_HEADER));
        assertEquals(X_IP_VALUE, request.getHeader(X_IP_HEADER));
    }

    @Test
    void testPlaceOrders() throws Exception {
        server.enqueue(new MockResponse().setBody("{placeOrders response}"));
        HttpUrl baseUrl = server.url("/placeOrders");

        when(urlBuilder.createBettingUrl(urlBuilder.PLACE_ORDERS)).thenReturn(baseUrl);
        when(requestBodyFactory.placeOrdersBody("1.23", 77L, "LAY", 2.0, 5.5))
                .thenReturn("{placeOrders body}");

        String response = apiClient.placeOrders("1.23", 77L, "LAY", 2.0, 5.5);
        RecordedRequest request = server.takeRequest();

        verify(requestBodyFactory).placeOrdersBody("1.23", 77L, "LAY", 2.0, 5.5);
        verify(urlBuilder).createBettingUrl(urlBuilder.PLACE_ORDERS);

        assertEquals("{placeOrders response}", response);
        assertEquals(baseUrl, request.getRequestUrl());
        assertEquals(APP_KEY, request.getHeader(X_APPLICATION_HEADER));
        assertEquals(TOKEN, request.getHeader(X_AUTHENTICATION_HEADER));
        assertTrue(request.getBody().toString().contains("{placeOrders body}"));
        assertTrue(request.getHeader(CONTENT_TYPE_HEADER).contains(CONTENT_TYPE_VALUE));
        assertEquals(ACCEPT_VALUE, request.getHeader(ACCEPT_HEADER));
        assertEquals(X_IP_VALUE, request.getHeader(X_IP_HEADER));
    }

    @Test
    void testCancelOrders() throws Exception {
        server.enqueue(new MockResponse().setBody("{cancelOrders response}"));
        HttpUrl baseUrl = server.url("/cancelOrders");

        when(urlBuilder.createBettingUrl(urlBuilder.CANCEL_ORDERS)).thenReturn(baseUrl);
        when(requestBodyFactory.cancelOrdersBody("1.23", "77")).thenReturn("{cancelOrders body}");

        String response = apiClient.cancelOrders("1.23", "77");
        RecordedRequest request = server.takeRequest();

        verify(requestBodyFactory).cancelOrdersBody("1.23", "77");
        verify(urlBuilder).createBettingUrl(urlBuilder.CANCEL_ORDERS);

        assertEquals("{cancelOrders response}", response);
        assertEquals(baseUrl, request.getRequestUrl());
        assertEquals(APP_KEY, request.getHeader(X_APPLICATION_HEADER));
        assertEquals(TOKEN, request.getHeader(X_AUTHENTICATION_HEADER));
        assertTrue(request.getBody().toString().contains("{cancelOrders body}"));
        assertTrue(request.getHeader(CONTENT_TYPE_HEADER).contains(CONTENT_TYPE_VALUE));
        assertEquals(ACCEPT_VALUE, request.getHeader(ACCEPT_HEADER));
        assertEquals(X_IP_VALUE, request.getHeader(X_IP_HEADER));
    }
}
