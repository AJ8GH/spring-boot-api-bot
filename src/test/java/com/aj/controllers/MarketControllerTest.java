package com.aj.controllers;

import com.aj.api.ApiClient;
import com.aj.deserialisation.JsonDeserialiser;
import com.aj.domain.bettingtypes.MarketCatalogue;
import com.aj.domain.bettingtypes.UserSession;
import com.aj.enrichment.Enricher;
import com.aj.esa.EsaClient;
import com.aj.esa.cache.MarketSubscriptionCache;
import com.aj.domain.esa.ResponseMessage;
import com.aj.repositories.MarketCatalogueRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class MarketControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    ApiClient apiClient;
    @MockBean
    JsonDeserialiser jsonDeserialiser;
    @MockBean
    Enricher enricher;
    @MockBean
    MarketCatalogueRepository marketCatalogueRepository;
    @MockBean
    MarketSubscriptionCache cache;
    @MockBean
    EsaClient esaClient;

    @BeforeEach
    void setup() throws JsonProcessingException {
        UserSession userSession = mock(UserSession.class);
        when(userSession.getStatus()).thenReturn("SUCCESS");
        when(apiClient.getUserSession()).thenReturn(userSession);

        MarketCatalogue catalogue = mock(MarketCatalogue.class);

        when(jsonDeserialiser.mapToMarketCatalogue(any())).thenReturn(List.of(catalogue));
    }

    @Test
    void testListMarketCatalogue() throws Exception {
        mockMvc.perform(get("/markets/listCatalogue/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("markets/listCatalogue"))
                .andExpect(content().string(containsString("Market Catalogue")));
    }

    @Test
    void testListMarketBook() throws Exception {
        mockMvc.perform(get("/markets/listBook/1.567"))
                .andExpect(status().isOk())
                .andExpect(view().name("markets/listBook"))
                .andExpect(content().string(containsString("Market Book")));
    }

    @Test
    void testNewMarketSubscription() throws Exception {
        mockMvc.perform(get("/markets/subscriptions/new/1.567"))
                .andExpect(status().isOk())
                .andExpect(view().name("markets/subscriptions/new"))
                .andExpect(content().string(containsString(
                        "Choose how many seconds to connect")));
    }

    @Test
    void testShowMarketSubscriptions() throws Exception {
        when(esaClient.pollStream()).thenReturn("Response");
        when(esaClient.getTimeout()).thenReturn(100);

        ResponseMessage message = ResponseMessage.builder()
                .ct("CT")
                .build();

        when(cache.getMessage(any())).thenReturn(message);
        when(jsonDeserialiser.mapToObject(any(), any())).thenReturn(message);

        mockMvc.perform(get("/markets/subscriptions/show/1.567"))
                .andExpect(status().isOk())
                .andExpect(view().name("markets/subscriptions/show"));
    }

    @Test
    void testShowMarketSubscriptionsSocketTimeout() throws Exception {
        when(esaClient.pollStream()).thenReturn("Response");
        when(esaClient.getTimeout()).thenReturn(0);

        ResponseMessage message = ResponseMessage.builder()
                .ct("CT")
                .build();

        when(cache.getMessage(any())).thenReturn(message);
        when(jsonDeserialiser.mapToObject(any(), any())).thenReturn(message);

        mockMvc.perform(get("/markets/subscriptions/show/1.567"))
                .andExpect(status().is(302))
                .andExpect(view().name("redirect:/"));
    }

    @Test
    void testMarketsSubscribe() throws Exception {
        ResponseMessage message = ResponseMessage.builder()
                .ct("CT")
                .build();

        when(esaClient.pollStream()).thenReturn("Response");
        when(jsonDeserialiser.mapToObject(any(), any())).thenReturn(message);

        mockMvc.perform(post("/markets/subscribe/1.567")
                        .param("timeout", "20"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/markets/subscriptions/show/1.567"));
    }

    @Test
    void testMarketsSubscriptionDelete() throws Exception {
        mockMvc.perform(post("/markets/subscriptions/delete/1.567"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/markets/listBook/1.567"));
    }
}
