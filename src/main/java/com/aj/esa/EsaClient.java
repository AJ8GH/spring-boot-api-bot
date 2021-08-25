package com.aj.esa;

import com.aj.domain.esa.AuthenticationMessage;
import com.aj.domain.esa.EsaMessage;
import com.aj.domain.esa.SubscriptionMessage;
import com.aj.domain.bettingtypes.UserSession;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

@Service
public class EsaClient {
    private final String SUBSCRIPTION_LOG_MESSAGE = "Subscribing to Market: {}";
    private final String AUTHENTICATION_LOG_MESSAGE = "Authenticating: {}";
    private final String INPUT_LOG_MESSAGE = "Message received: {}";
    private final Logger LOGGER = LoggerFactory.getLogger(EsaClient.class);
    private final SocketFactory socketFactory;
    private final ObjectMapper mapper;
    private final MessageFactory messageFactory;
    private BufferedReader reader;
    private PrintWriter writer;
    private UserSession userSession;
    private Socket client;
    private boolean isConnected;

    public EsaClient(
            SocketFactory socketFactory,
            ObjectMapper mapper,
            MessageFactory messageFactory) {
        this.socketFactory = socketFactory;
        this.mapper = mapper;
        this.messageFactory = messageFactory;
    }

    public void setUserSession(UserSession userSession) {
        this.userSession = userSession;
    }

    public BufferedReader getReader() {
        return reader;
    }

    public void connect(int timeout) throws IOException {
        client = socketFactory.getDefault();
        client.setSoTimeout(timeout * 1000);
        InputStreamReader in = new InputStreamReader(client.getInputStream());
        reader = new BufferedReader(in);
        writer = new PrintWriter(client.getOutputStream());
        isConnected = true;
        pollStream();
    }

    public String authenticate() throws IOException {
        AuthenticationMessage message = messageFactory.authenticationMessage(
                userSession.getEsaAppKey(),
                userSession.getToken());

        return writeToStream(AUTHENTICATION_LOG_MESSAGE, message);
    }

    public String subscribeToMarkets(String marketId) throws IOException {
        SubscriptionMessage message = messageFactory
                .marketSubscriptionMessage(marketId);

        return writeToStream(SUBSCRIPTION_LOG_MESSAGE, message);
    }

    public String pollStream() throws IOException {
        String data = reader.readLine();
        LOGGER.info("Message received: {}", data);
        return data;
    }

    public void close() throws IOException {
        client.close();
        isConnected = false;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public int getTimeout() throws SocketException {
        return client.getSoTimeout();
    }

    private String writeToStream(String logMessage, EsaMessage message)
            throws IOException {
        String payload = mapper.writeValueAsString(message);
        writer.println(payload);
        LOGGER.info(logMessage, payload);
        writer.flush();
        return pollStream();
    }
}
