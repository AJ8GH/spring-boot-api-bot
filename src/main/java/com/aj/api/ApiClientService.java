package com.aj.api;

import com.aj.models.UserSession;

import java.io.IOException;

public interface ApiClientService {
    public void setUserSession(UserSession userSession) throws IOException;
    public UserSession getUserSession();
    public String login(String username, String password) throws IOException;
    public String listEventTypes() throws IOException;
    public String listEvents(long eventTypeId) throws IOException;
    public String listMarketCatalogue(String filter, String id) throws IOException;
    public String listMarketBook(String marketId) throws IOException;
    public String listCurrentOrders(String betId) throws IOException;
    public String listCurrentOrders() throws IOException;
    public String placeOrders(String marketId, long selectionId, String side,
                              double size, double price) throws IOException;
    public String cancelOrders(String marketId, long betId) throws IOException;
}
