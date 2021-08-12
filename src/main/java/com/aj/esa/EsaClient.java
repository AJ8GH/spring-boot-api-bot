package com.aj.esa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;

public class EsaClient {
    private final Logger LOGGER = LoggerFactory.getLogger(EsaClient.class);
    private final SocketFactory socketFactory;
    private BufferedReader reader;
    private PrintWriter writer;

    public EsaClient(SocketFactory socketFactory) {
        this.socketFactory = socketFactory;
    }

    public String connect() throws IOException {
        Socket socket = socketFactory.getDefault();

        InputStreamReader in = new InputStreamReader(socket.getInputStream());
        reader = new BufferedReader(in);
        writer = new PrintWriter(socket.getOutputStream());

        String connectionMessage = reader.readLine();
        LOGGER.info(connectionMessage);

        return connectionMessage;
    }

    public String authenticate(String appKey, String sessionToken) throws IOException {
        String payLoad = String.format("{\"op\":\"authentication\"," +
                "\"appKey\":\"%s\"," +
                "\"session\":\"%s\"}", appKey, sessionToken);

        writer.println(payLoad);
        writer.flush();

        String authenticationMessage = reader.readLine();
        LOGGER.info(authenticationMessage);

        return authenticationMessage;
    }

    public BufferedReader getReader() {
        return reader;
    }
}
