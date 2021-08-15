package com.aj.esa;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.Socket;

@Service
public class SocketFactory {
    private static final String DEFAULT_HOST = "esa.nxt.internal";
    private static final int DEFAULT_PORT = 443;

    public Socket getDefault() throws IOException {
        return new Socket(DEFAULT_HOST, DEFAULT_PORT);
    }
}
