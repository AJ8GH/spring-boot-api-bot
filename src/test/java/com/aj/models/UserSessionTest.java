package com.aj.models;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class UserSessionTest {

    @Test
    void testUserSession() {
        UserSession userSession = new UserSession(9L, "status", "token",
                "appKey", "esaAppKey", "product", "error", "props");

        assertEquals(9L, userSession.getId());
        assertEquals("status", userSession.getStatus());
        assertEquals("token", userSession.getToken());
        assertEquals("appKey", userSession.getAppKey());
        assertEquals("esaAppKey", userSession.getEsaAppKey());
        assertEquals("product", userSession.getProduct());
        assertEquals("error", userSession.getError());
        assertEquals("props", userSession.getProps());
    }

    @Test
    void setId() {
        UserSession userSession = new UserSession();
        userSession.setId(9L);

        assertEquals(9L, userSession.getId());
    }

    @Test
    void testToString() {
        UserSession userSession = new UserSession(9L, "status", "token",
                "appKey", "esaAppKey", "product", "error", "props");

        assertTrue(userSession.toString().contains(userSession.getId().toString()));
        assertTrue(userSession.toString().contains(userSession.getStatus()));
        assertTrue(userSession.toString().contains(userSession.getToken()));
        assertTrue(userSession.toString().contains(userSession.getAppKey()));
        assertTrue(userSession.toString().contains(userSession.getEsaAppKey()));
        assertTrue(userSession.toString().contains(userSession.getError()));
    }

    @Test
    void loadAppKey() throws IOException {
        UserSession userSession = new UserSession();
        userSession.setProps("test.properties");
        userSession.loadAppKey();

        assertEquals("appKey", userSession.getAppKey());
        assertEquals("esaAppKey", userSession.getEsaAppKey());
        assertEquals(userSession, UserSession.getCurrentSession());
    }
}
