package com.aj.esa;

import com.aj.esa.models.AuthenticationMessage;
import com.aj.models.UserSession;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

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

    @Test
    void connect() throws IOException {
        String result = client.connect(3);

        verify(factory).getDefault();
        verify(socket).getInputStream();
        verify(socket).getOutputStream();
        verify(socket).setSoTimeout(3 * 1000);
        assertEquals(client.getReader().readLine(), result);
    }

    @Test
    void authenticate() throws IOException {
        client.connect(3);

        AuthenticationMessage message = AuthenticationMessage.builder()
                .op("authentication")
                .appKey("AppKey")
                .session("Session").build();

        ObjectMapper om = new ObjectMapper();
        String payLoad = om.writeValueAsString(message);

        when(mapper.writeValueAsString(any(AuthenticationMessage.class)))
                .thenReturn(payLoad);

        String result = client.authenticate();

        assertArrayEquals(outputStream.toByteArray(), (payLoad + "\n").getBytes());
        assertEquals(client.getReader().readLine(), result);
    }
}
