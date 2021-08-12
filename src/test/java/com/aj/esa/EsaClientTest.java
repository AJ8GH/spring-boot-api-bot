package com.aj.esa;

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
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() throws IOException {
        factory = mock(SocketFactory.class);
        socket = mock(Socket.class);
        when(factory.getDefault()).thenReturn(socket);

        outputStream = new ByteArrayOutputStream();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(new byte[] {});

        when(socket.getOutputStream()).thenReturn(outputStream);
        when(socket.getInputStream()).thenReturn(inputStream);
    }

    @Test
    void connect() throws IOException {
        EsaClient client = new EsaClient(factory);
        String result = client.connect();

        verify(factory).getDefault();
        verify(socket).getInputStream();
        verify(socket).getOutputStream();
        assertEquals(client.getReader().readLine(), result);
    }

    @Test
    void authenticate() throws IOException {
        EsaClient client = new EsaClient(factory);
        client.connect();

        String result = client.authenticate("AppKey", "Session");
        String payLoad = "{\"op\":\"authentication\"," +
                "\"appKey\":\"AppKey\"," +
                "\"session\":\"Session\"}\n";

        assertArrayEquals(outputStream.toByteArray(), payLoad.getBytes());
    }
}
