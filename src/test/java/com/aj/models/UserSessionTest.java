package com.aj.models;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class UserSessionTest {

    @Test
    void testUserSession() {
        UserSession userSession = UserSession.builder()
                .id(9L)
                .status("status")
                .token("token")
                .appKey("appKey")
                .esaAppKey("esaAppKey")
                .product("product")
                .error("error")
                .props("props")
                .build();

        assertTrue(userSession.toString().contains(userSession.getId().toString()));
        assertTrue(userSession.toString().contains(userSession.getStatus()));
        assertTrue(userSession.toString().contains(userSession.getToken()));
        assertTrue(userSession.toString().contains(userSession.getAppKey()));
        assertTrue(userSession.toString().contains(userSession.getEsaAppKey()));
        assertTrue(userSession.toString().contains(userSession.getError()));
    }

    @Test
    void setId() {
        UserSession userSession = new UserSession();
        userSession.setId(9L);

        assertEquals(9L, userSession.getId());
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
