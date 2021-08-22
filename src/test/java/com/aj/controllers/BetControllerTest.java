package com.aj.controllers;

import com.aj.api.ApiClientService;
import com.aj.deserialisation.DeserialisationService;
import com.aj.domain.bettingtypes.*;
import com.aj.enrichment.EnrichmentService;
import com.aj.repositories.BetRepository;
import com.aj.repositories.CancelExecutionReportRepository;
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
import static org.hamcrest.Matchers.in;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BetControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    ApiClientService apiClient;
    @MockBean
    DeserialisationService jsonDeserialiser;
    @MockBean
    CancelExecutionReportRepository reportRepository;
    @MockBean
    BetRepository betRepository;
    @MockBean
    EnrichmentService enricher;

    @BeforeEach
    void setup() throws JsonProcessingException {
        UserSession userSession = mock(UserSession.class);
        when(userSession.getStatus()).thenReturn("SUCCESS");
        when(apiClient.getUserSession()).thenReturn(userSession);

        Bet bet = mock(Bet.class);
        MarketCatalogue catalogue = mock(MarketCatalogue.class);
        when(jsonDeserialiser.mapToBetList(any())).thenReturn(List.of(bet));
        when(jsonDeserialiser.mapToMarketCatalogue(any())).thenReturn(List.of(catalogue));
    }

    @Test
    void testListCurrentBets() throws Exception {
        mockMvc.perform(get("/bets/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("bets/list"))
                .andExpect(content().string(containsString("Current Bets")));
    }

    @Test
    void testGetBetsNew() throws Exception {
        mockMvc.perform(get("/bets/new/1.123/456"))
        .andExpect(status().isOk())
        .andExpect(view().name("bets/new"))
        .andExpect(content().string(containsString("Place Bet")));
    }

    @Test
    void testPostBetsNew() throws Exception {
        Bet bet = Bet.builder().betId("99").build();
        when(jsonDeserialiser.mapToObject(any(), any())).thenReturn(bet);

        mockMvc.perform(post("/bets/new")
                        .param("price", "3")
                        .param("size", "2")
                        .param("marketId", "1.2345")
                        .param("selectionId", "3427")
                        .param("side", "BACK"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/bets/show/99"));
    }

    @Test
    void testShowBet() throws Exception {
        mockMvc.perform(get("/bets/show/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("bets/show"))
                .andExpect(content().string(containsString("Bet Confirmation")));
    }

    @Test
    void testShowCancelExecutionReport() throws Exception {
        List<InstructionReport> instructions = List.of(new InstructionReport());
        var report = new CancelExecutionReport();
        report.setInstructionReports(instructions);
        report.setStatus("FAILURE");
        when(reportRepository.findById(1L)).thenReturn(java.util.Optional.of(report));

        mockMvc.perform(get("/bets/delete/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("bets/delete"))
                .andExpect(content().string(containsString("Cancel Execution Report")));
    }
}
