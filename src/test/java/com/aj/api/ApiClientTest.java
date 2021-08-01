package com.aj.api;

import com.aj.models.UserSession;
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
    private static final String APP_KEY = "APP_KEY";
    private static final String TOKEN = "TOKEN";
    private MockWebServer server;

    @BeforeEach
    public void setUp() throws IOException {
        UserSession userSession = mock(UserSession.class);
        when(userSession.getToken()).thenReturn(TOKEN);
        when(userSession.getAppKey()).thenReturn(APP_KEY);
        ApiClient.setUserSession(userSession);

        server = new MockWebServer();
        server.start();
    }

    @AfterEach
    public void tearDown() throws IOException {
        server.shutdown();
    }

    @Test
    public void getUserSession() throws IOException {
        UserSession userSession = mock(UserSession.class);
        ApiClient.setUserSession(userSession);
        assertEquals(userSession, ApiClient.getUserSession());
    }

    @Test
    void login() throws Exception {
        String mockResponse = "{login response}";
        server.enqueue(new MockResponse().setBody(mockResponse));
        HttpUrl baseUrl = server.url("?username=username&password=password");

        RequestBodyBuilder requestBodyBuilder = mock(RequestBodyBuilder.class);
        UrlBuilder urlBuilder = mock(UrlBuilder.class);
        when(urlBuilder.createLoginUrl("username", "password"))
                .thenReturn(baseUrl);

        ApiClient apiClient = new ApiClient(urlBuilder, requestBodyBuilder);
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
    void listEventTypes() throws Exception {
        String mockResponse = "{list event types response}";
        server.enqueue(new MockResponse().setBody(mockResponse));
        HttpUrl baseUrl = server.url("/listEventTypes");

        UrlBuilder urlBuilder = mock(UrlBuilder.class);
        RequestBodyBuilder requestBodyBuilder = mock(RequestBodyBuilder.class);
        when(urlBuilder.createBettingUrl(UrlBuilder.LIST_EVENT_TYPES)).thenReturn(baseUrl);
        when(requestBodyBuilder.listEventTypesBody()).thenReturn("{event types body}");

        ApiClient apiClient = new ApiClient(urlBuilder, requestBodyBuilder);
        String response = apiClient.listEventTypes();
        RecordedRequest request = server.takeRequest();

        assertEquals(mockResponse, response);
        assertEquals(baseUrl, request.getRequestUrl());
        assertTrue(request.getBody().toString().contains("{event types body}"));
        assertEquals(APP_KEY, request.getHeader(X_APPLICATION_HEADER));
        assertEquals(TOKEN, request.getHeader(X_AUTHENTICATION_HEADER));
        assertTrue(request.getHeader(CONTENT_TYPE_HEADER).contains(CONTENT_TYPE_VALUE));
        assertEquals(ACCEPT_VALUE, request.getHeader(ACCEPT_HEADER));
        assertEquals(X_IP_VALUE, request.getHeader(X_IP_HEADER));
    }

    @Test
    void listCurrentOrders() throws Exception {
        String mockResponse = "{list current orders response}";
        server.enqueue(new MockResponse().setBody(mockResponse));
        HttpUrl baseUrl = server.url("/listCurrentOrders");

        UrlBuilder urlBuilder = mock(UrlBuilder.class);
        RequestBodyBuilder requestBodyBuilder = mock(RequestBodyBuilder.class);
        when(urlBuilder.createBettingUrl(UrlBuilder.LIST_CURRENT_ORDERS)).thenReturn(baseUrl);

        ApiClient apiClient = new ApiClient(urlBuilder, requestBodyBuilder);
        String response = apiClient.listCurrentOrders();
        RecordedRequest request = server.takeRequest();

        assertEquals(mockResponse, response);
        assertEquals(baseUrl, request.getRequestUrl());
        assertEquals(APP_KEY, request.getHeader(X_APPLICATION_HEADER));
        assertEquals(TOKEN, request.getHeader(X_AUTHENTICATION_HEADER));
        assertTrue(request.getHeader(CONTENT_TYPE_HEADER).contains(CONTENT_TYPE_VALUE));
        assertEquals(ACCEPT_VALUE, request.getHeader(ACCEPT_HEADER));
        assertEquals(X_IP_VALUE, request.getHeader(X_IP_HEADER));
    }

    @Test
    void listEvents() throws Exception {
        String mockResponse = "{list events response}";
        server.enqueue(new MockResponse().setBody(mockResponse));
        HttpUrl baseUrl = server.url("/listEvents");

        UrlBuilder urlBuilder = mock(UrlBuilder.class);
        RequestBodyBuilder requestBodyBuilder = mock(RequestBodyBuilder.class);
        when(urlBuilder.createBettingUrl(UrlBuilder.LIST_EVENTS)).thenReturn(baseUrl);
        when(requestBodyBuilder.listEventsBody(1L)).thenReturn("{list events body}");

        ApiClient apiClient = new ApiClient(urlBuilder, requestBodyBuilder);
        String response = apiClient.listEvents(1L);
        RecordedRequest request = server.takeRequest();

        verify(requestBodyBuilder).listEventsBody(1L);
        assertEquals(mockResponse, response);
        assertEquals(baseUrl, request.getRequestUrl());
        assertEquals(APP_KEY, request.getHeader(X_APPLICATION_HEADER));
        assertEquals(TOKEN, request.getHeader(X_AUTHENTICATION_HEADER));
        assertTrue(request.getBody().toString().contains("{list events body}"));
        assertTrue(request.getHeader(CONTENT_TYPE_HEADER).contains(CONTENT_TYPE_VALUE));
        assertEquals(ACCEPT_VALUE, request.getHeader(ACCEPT_HEADER));
        assertEquals(X_IP_VALUE, request.getHeader(X_IP_HEADER));
    }
}
