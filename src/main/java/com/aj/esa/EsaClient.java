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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EsaClient {
    private final Logger LOG = LoggerFactory.getLogger(EsaClient.class);
    private final SocketFactory socketFactory;
    private final ObjectMapper mapper;
    private BufferedReader reader;
    private PrintWriter writer;
    private UserSession userSession;
    private Socket client;
    private boolean isConnected;

    public EsaClient(
            SocketFactory socketFactory,
            UserSession userSession,
            ObjectMapper mapper) {
        this.socketFactory = socketFactory;
        this.userSession = userSession;
        this.mapper = mapper;
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
        LOG.info(reader.readLine());
        isConnected = true;
    }

    public String authenticate() throws IOException {
        AuthenticationMessage message = AuthenticationMessage.builder()
                .op("authentication")
                .appKey(userSession.getEsaAppKey())
                .session(userSession.getToken()).build();

        String payLoad = mapper.writeValueAsString(message);
        writer.println(payLoad);
        writer.flush();
        return getLatest();
    }

    public String subscribeToMarkets(String marketId) throws IOException {
        List<String> marketIds = List.of(marketId);
        Map<String, List<String>> marketFilter = new HashMap<>();
        marketFilter.put("marketIds", marketIds);

        List<String> fields = List.of("EX_BEST_OFFERS_DISP", "EX_BEST_OFFERS", "EX_MARKET_DEF");
        Map<String, List<String>> marketDataFilter = new HashMap<>();
        marketDataFilter.put("fields", fields);

        MarketSubscriptionMessage message = MarketSubscriptionMessage.builder()
                .op("marketSubscription")
                .marketFilter(marketFilter)
                .marketDataFilter(marketDataFilter)
                .build();

        String payload = mapper.writeValueAsString(message);
        writer.println(payload);

        writer.flush();
        return getLatest();
    }

    public String getLatest() throws IOException {
        String data = reader.readLine();
        LOG.info(data);
        return data;
    }

    public void close() throws IOException {
        client.close();
        isConnected = false;
    }

    public boolean isConnected() {
        return isConnected;
    }
}
