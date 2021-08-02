package com.aj.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.containsString;


@SpringBootTest
@AutoConfigureMockMvc
class UserSessionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testLogIn() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Betfair API Bot")))
                .andExpect(content().string(containsString("Login to get started")));
    }

    @Test
    void testIndexRouteWithNoSession() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().is(302))
                .andExpect(view().name("redirect:/login"));
    }
}