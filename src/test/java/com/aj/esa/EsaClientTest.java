package com.aj.esa;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class EsaClientTest {

    @Test
    void connect() throws IOException {
        SocketFactory factory = mock(SocketFactory.class);
        Socket socket = mock(Socket.class);
        when(factory.getDefault()).thenReturn(socket);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(new byte[] {});

        when(socket.getOutputStream()).thenReturn(outputStream);
        when(socket.getInputStream()).thenReturn(inputStream);

        EsaClient client = new EsaClient(factory);
        String result = client.connect();

        verify(factory).getDefault();
        verify(socket).getInputStream();
        verify(socket).getOutputStream();
        assertEquals(client.getReader().readLine(), result);
    }
}