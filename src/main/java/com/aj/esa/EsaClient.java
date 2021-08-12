package com.aj.esa;

import com.aj.models.UserSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;

public class EsaClient {
    private final Logger LOGGER = LoggerFactory.getLogger(EsaClient.class);
    private final SocketFactory socketFactory;
    private BufferedReader reader;
    private PrintWriter writer;
    private UserSession userSession;

    public EsaClient(SocketFactory socketFactory, UserSession userSession) {
        this.socketFactory = socketFactory;
        this.userSession = userSession;
    }

    public BufferedReader getReader() {
        return reader;
    }

    public String connect(int timeout) throws IOException {
        Socket socket = socketFactory.getDefault();
        socket.setSoTimeout(timeout * 1000);
        InputStreamReader in = new InputStreamReader(socket.getInputStream());
        reader = new BufferedReader(in);
        writer = new PrintWriter(socket.getOutputStream());
        return logAndReturnResponse();
    }

    public String authenticate() throws IOException {
        String payLoad = String.format("{\"op\":\"authentication\"," +
                "\"appKey\":\"%s\",\"session\":\"%s\"}",
                userSession.getEsaAppKey(), userSession.getToken());
        writer.println(payLoad);
        writer.flush();
        return logAndReturnResponse();
    }

    private String logAndReturnResponse() throws IOException {
        String response = reader.readLine();
        LOGGER.info(response);
        return response;
    }
}
