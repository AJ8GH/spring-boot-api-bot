package com.aj.esa;

import com.aj.esa.models.AuthenticationMessage;
import com.aj.models.UserSession;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;

public class EsaClient {
    private final Logger LOGGER = LoggerFactory.getLogger(EsaClient.class);
    private final SocketFactory socketFactory;
    private final ObjectMapper mapper;
    private BufferedReader reader;
    private PrintWriter writer;
    private UserSession userSession;

    public EsaClient(
            SocketFactory socketFactory,
            UserSession userSession,
            ObjectMapper mapper) {
        this.socketFactory = socketFactory;
        this.userSession = userSession;
        this.mapper = mapper;
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
        AuthenticationMessage message = AuthenticationMessage.builder()
                .op("authentication")
                .appKey(userSession.getEsaAppKey())
                .session(userSession.getToken()).build();

        String payLoad = mapper.writeValueAsString(message);

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
