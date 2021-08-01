package com.aj.controllers;

import com.aj.models.UserSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EventControllerTest {

//     @Autowired
//     private MockMvc mockMvc;
//
//     @Test
//     void testListEventTypes() throws Exception {
//         UserSession userSession = new UserSession();
//         userSession.loadAppKey();
//
//         mockMvc.perform(get("/listEventTypes"))
//                 .andExpect(status().isOk())
//                 .andExpect(content().string(containsString("Betfair API Bot")));
//     }
}
