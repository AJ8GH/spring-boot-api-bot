package com.aj.api;

import java.io.IOException;

public interface ApiClientService {
    public String login(String username, String password) throws IOException;
    public String listEventTypes() throws IOException;
    public String listEvents(long eventTypeId) throws IOException;
    public String listMarketCatalogue(long eventId) throws IOException;
    public String listMarketBook(String marketId) throws IOException;
    public String listCurrentOrders() throws IOException;
    public String placeOrders(String marketId, long selectionId, String side,
                              double size, double price) throws IOException;
    public String cancelOrders(String marketId, long betId) throws IOException;
}
