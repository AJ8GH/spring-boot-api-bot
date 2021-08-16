package com.aj.esa;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class SocketFactoryTest {
    private final String TEST_HOST = "www.example.com";
    private final int DEFAULT_PORT = 443;

    @Test
    void testGetDefault() throws IOException {
        SocketFactory factory = new SocketFactory();
        factory.setHost(TEST_HOST);
        Socket socket = factory.getDefault();

        assertTrue(socket.getInetAddress().toString().contains(TEST_HOST));
        assertEquals(DEFAULT_PORT, socket.getPort());
    }
}
