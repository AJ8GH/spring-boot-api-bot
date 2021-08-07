package com.aj.controllers;

import com.aj.api.ApiClientService;
import com.aj.deserialisation.DeserialisationService;
import com.aj.repositories.MarketBookRepository;
import com.aj.repositories.RunnerRepository;
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
class MarketControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    ApiClientService apiClient;
    @MockBean
    DeserialisationService jsonDeserialiser;
    @MockBean
    MarketBookRepository marketBookRepository;
    @MockBean
    RunnerRepository runnerRepository;

    @Test
    void testListMarketCatalogue() throws Exception {
        mockMvc.perform(get("/listMarketCatalogue/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("listMarketCatalogue"))
                .andExpect(content().string(containsString("Market Catalogue")));
    }

    @Test
    void testListMarketBook() throws Exception {
        mockMvc.perform(get("/listMarketBook/1.567"))
                .andExpect(status().isOk())
                .andExpect(view().name("listMarketBook"))
                .andExpect(content().string(containsString("Market Book")));
    }
}
