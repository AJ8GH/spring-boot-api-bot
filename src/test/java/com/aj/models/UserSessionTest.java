package com.aj.models;

import org.junit.jupiter.api.Test;

import java.io.IOException;

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

        assertEquals(expectedOutput, userSession.toString());
    }

    @Test
    void loadAppKey() throws IOException {
        UserSession userSession = new UserSession();
        userSession.setProps("test.properties");
        userSession.loadAppKey();

        assertEquals("appKey", userSession.getAppKey());
    }
}
