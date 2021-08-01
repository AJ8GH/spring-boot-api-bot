package com.aj.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserSessionTest {

    @Test
    void testUserSession() {
        UserSession userSession = new UserSession("status", "token",
                "appKey", "product", "error");

        assertEquals("status", userSession.getStatus());
        assertEquals("token", userSession.getToken());
        assertEquals("appKey", userSession.getAppKey());
        assertEquals("product", userSession.getProduct());
        assertEquals("error", userSession.getError());
    }

    @Test
    void setId() {
        UserSession userSession = new UserSession();
        userSession.setId(9L);

        assertEquals(9L, userSession.getId());
    }

    @Test
    void setAppKey() {
        UserSession userSession = new UserSession();
        userSession.setAppKey("appKey");

        assertEquals("appKey", userSession.getAppKey());
    }

    @Test
    void testToString() {
        UserSession userSession = new UserSession();
        String expectedOutput = "UserSession{" +
                "id=" + userSession.getId() +
                ", status='" + userSession.getStatus() + '\'' +
                ", token='" + userSession.getToken() + '\'' +
                ", appKey='" + userSession.getAppKey() + '\'' +
                ", product='" + userSession.getProduct() + '\'' +
                ", error='" + userSession.getError() + '\'' +
                '}';
    }

    @Test
    void loadAppKey() {
    }
}
