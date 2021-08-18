package com.aj.esa;

import com.aj.esa.models.AuthenticationMessage;
import com.aj.esa.models.MarketSubscriptionMessage;
import com.aj.models.UserSession;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EsaClient {
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
        getLatest();
    }

    public String authenticate() throws IOException {
        AuthenticationMessage message = messageFactory.authenticationMessage(
                userSession.getEsaAppKey(),
                userSession.getToken());

        String payLoad = mapper.writeValueAsString(message);
        writer.println(payLoad);
        LOGGER.info("Authenticating: {}", payLoad);
        writer.flush();
        return getLatest();
    }

    public String subscribeToMarkets(String marketId) throws IOException {
        MarketSubscriptionMessage message = messageFactory
                .marketSubscriptionMessage(marketId);

        String payload = mapper.writeValueAsString(message);
        writer.println(payload);
        LOGGER.info("Subscribing to Market: {}", payload);
        writer.flush();
        return getLatest();
    }

    public String getLatest() throws IOException {
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
}
