package com.aj.esa;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.Socket;

@Service
@Setter
@Getter
public class SocketFactory {
    private String host = "esa.nxt.internal";
    private int port = 443;

    public Socket getDefault() throws IOException {
        return new Socket(host, port);
    }
}
