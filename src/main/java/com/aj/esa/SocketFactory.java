package com.aj.esa;

import java.io.IOException;
import java.net.Socket;

public class SocketFactory {
    private static final String DEFAULT_HOST = "esa.nxt.internal";
    private static final int DEFAULT_PORT = 443;

    public Socket getDefault() throws IOException {
        return new Socket(DEFAULT_HOST, DEFAULT_PORT);
    }
}
