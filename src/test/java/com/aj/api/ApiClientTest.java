package com.aj.api;

import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void loginCall() throws Exception {

        MockWebServer server = new MockWebServer();
        String expectedResponse = "test response - request has been made";
        server.enqueue(new MockResponse().setBody(expectedResponse));
        server.start();

        ApiClient apiClient = new ApiClient();
        HttpUrl baseUrl = server.url("/test-url");

        String actualResponse = apiClient.loginCall(baseUrl);

        RecordedRequest request = server.takeRequest();
        HttpUrl requestUrl = request.getRequestUrl();
        String xApplication = request.getHeader(X_APPLICATION_HEADER);
        String contentType = request.getHeader(CONTENT_TYPE_HEADER);
        String accept = request.getHeader(ACCEPT_HEADER);
        String xIp = request.getHeader(X_IP_HEADER);

        assertEquals(expectedResponse, actualResponse);
        assertEquals(baseUrl, requestUrl);
        assertEquals(X_APPLICATION_VALUE, xApplication);
        assertEquals(CONTENT_TYPE_VALUE, contentType);
        assertEquals(ACCEPT_VALUE, accept);
        assertEquals(X_IP_VALUE, xIp);
    }
}
