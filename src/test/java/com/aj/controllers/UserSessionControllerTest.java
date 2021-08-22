package com.aj.controllers;

import com.aj.api.ApiClientService;
import com.aj.deserialisation.DeserialisationService;
import com.aj.domain.UserSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserSessionControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    ApiClientService apiClient;
    @MockBean
    DeserialisationService jsonDeserialiser;

    @Test
    void testLogIn() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Betfair API Bot")))
                .andExpect(content().string(containsString("Login to get started")));
    }

    @Test
    void testIndexRouteWithoutSession() throws Exception {
        UserSession userSession = new UserSession();
        userSession.setStatus("FAIL");
        userSession.setProps("test.properties");
        when(apiClient.getUserSession()).thenReturn(userSession);

        mockMvc.perform(get("/"))
                .andExpect(status().is(302))
                .andExpect(view().name("redirect:/login"));
    }

    @Test
    void testIndexRouteWithSession() throws Exception {
        UserSession userSession = new UserSession();
        userSession.setStatus("SUCCESS");
        userSession.setProps("test.properties");
        when(apiClient.getUserSession()).thenReturn(userSession);

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }
}
