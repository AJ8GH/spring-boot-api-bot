package com.aj.controllers;

import com.aj.api.ApiClient;
import com.aj.deserialisation.JsonDeserialiser;
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
public class MarketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ApiClient apiClient;

    @MockBean
    JsonDeserialiser jsonDeserialiser;

    @Test
    void testListMarketCatalogue() throws Exception {
        mockMvc.perform(get("/listMarketCatalogue/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("listMarketCatalogue"))
                .andExpect(content().string(containsString("Market Catalogue")));
    }
}
