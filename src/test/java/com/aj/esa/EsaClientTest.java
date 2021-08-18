package com.aj.esa;

import com.aj.esa.models.AuthenticationMessage;
import com.aj.esa.models.MarketSubscriptionMessage;
import com.aj.models.UserSession;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class EsaClientTest {
    private SocketFactory factory;
    private Socket socket;
    private ObjectMapper mapper;
    private ByteArrayOutputStream outputStream;
    private EsaClient client;

    @BeforeEach
    void setUp() throws IOException {
        factory = mock(SocketFactory.class);
        socket = mock(Socket.class);
        when(factory.getDefault()).thenReturn(socket);

        outputStream = new ByteArrayOutputStream();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(new byte[] {});

        when(socket.getOutputStream()).thenReturn(outputStream);
        when(socket.getInputStream()).thenReturn(inputStream);

        UserSession userSession = mock(UserSession.class);
        when(userSession.getEsaAppKey()).thenReturn("AppKey");
        when(userSession.getToken()).thenReturn("Session");

        mapper = mock(ObjectMapper.class);

        client = new EsaClient(factory, userSession, mapper);
    }

    @AfterEach
    void tearDown() throws IOException {
        client.close();
    }

    @Test
    void connect() throws IOException {
        client.connect(3);

        verify(factory).getDefault();
        verify(socket).getInputStream();
        verify(socket).getOutputStream();
        verify(socket).setSoTimeout(3 * 1000);
    }

    @Test
    void authenticate() throws IOException {
        AuthenticationMessage message = AuthenticationMessage.builder()
                .op("authentication")
                .appKey("AppKey")
                .session("Session").build();

        ObjectMapper om = new ObjectMapper();
        String payLoad = om.writeValueAsString(message);

        when(mapper.writeValueAsString(any(AuthenticationMessage.class)))
                .thenReturn(payLoad);

        client.connect(3);
        String response = client.authenticate();

        assertArrayEquals((payLoad + "\n").getBytes(), outputStream.toByteArray());
        assertEquals(response, client.getReader().readLine());
    }

    @Test
    void subscribeToMarkets() throws IOException {
        List<String> marketIds = List.of("1.111");
        Map<String, List<String>> marketFilter = new HashMap<>();
        marketFilter.put("marketIds", marketIds);

        List<String> fields = List.of("EX_BEST_OFFERS_DISP", "EX_BEST_OFFERS");
        Map<String, List<String>> marketDataFilter = new HashMap<>();
        marketDataFilter.put("fields", fields);

        MarketSubscriptionMessage message = MarketSubscriptionMessage.builder()
                .op("marketSubscription")
                .marketFilter(marketFilter)
                .marketDataFilter(marketDataFilter)
                .build();

        ObjectMapper om = new ObjectMapper();
        String payLoad = om.writeValueAsString(message);

        when(mapper.writeValueAsString(any(MarketSubscriptionMessage.class)))
                .thenReturn(payLoad);

        client.connect(3);
        client.authenticate();
        String response = client.subscribeToMarkets("1.111");

        // assertArrayEquals((payLoad + "\n").getBytes(), outputStream.toByteArray());
        // assertEquals(response, client.getReader().readLine());
    }

    @Test
    void isConnected() throws IOException {
        assertFalse(client.isConnected());

        client.connect(1);
        assertTrue(client.isConnected());

        client.close();
        assertFalse(client.isConnected());
    }

    @Test
    void getTimeout() throws IOException {
        client.connect(3);
        client.getTimeout();

        verify(socket).getSoTimeout();
    }
}
