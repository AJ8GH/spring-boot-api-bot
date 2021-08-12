package com.aj.esa;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;

class SocketFactoryTest {
    private final String DEFAULT_HOST = "esa.nxt.internal";
    private final int DEFAULT_PORT = 443;

    @Test
    void testGetDefault() throws IOException {
        SocketFactory factory = new SocketFactory();
        Socket socket = factory.getDefault();

        assertTrue(socket.getInetAddress().toString().contains(DEFAULT_HOST));
        assertEquals(DEFAULT_PORT, socket.getPort());
    }
}
