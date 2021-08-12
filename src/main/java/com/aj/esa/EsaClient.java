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

    public void authenticate() throws IOException {
        writer.println("{\"op\":\"authentication\"," +
                "\"appKey\":\"h5rA96c1fEVxJYLt\"," +
                "\"session\":\"larkHPDxNAKNJMTRTyB66K0fh9STMh1s5q/XQhVddh4=\"}");
        writer.flush();

        String authenticationMessage = reader.readLine();
        // LOGGER.info(authenticationMessage);
    }

    public BufferedReader getReader() {
        return reader;
    }
}
