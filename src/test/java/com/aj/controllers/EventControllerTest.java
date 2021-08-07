package com.aj.controllers;

import com.aj.api.ApiClientService;
import com.aj.deserialisation.DeserialisationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ApiClientService apiClient;

    @MockBean
    DeserialisationService jsonDeserialiser;

    @Test
    void testListEventTypes() throws Exception {
        mockMvc.perform(get("/listEventTypes"))
                .andExpect(status().isOk())
                .andExpect(view().name("listEventTypes"))
                .andExpect(content().string(containsString("Event Types")));
    }

    @Test
    void testListEvents() throws Exception {
        mockMvc.perform(get("/listEvents/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("listEvents"))
                .andExpect(content().string(containsString("Events")));
    }
}
