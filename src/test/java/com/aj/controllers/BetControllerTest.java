package com.aj.controllers;

import com.aj.api.ApiClientService;
import com.aj.deserialisation.DeserialisationService;
import com.aj.models.Bet;
import com.aj.models.CancelExecutionReport;
import com.aj.models.UserSession;
import com.aj.repositories.BetRepository;
import com.aj.repositories.CancelExecutionReportRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

    @BeforeEach
    void setup() {
        UserSession userSession = mock(UserSession.class);
        when(userSession.getStatus()).thenReturn("SUCCESS");
        when(apiClient.getUserSession()).thenReturn(userSession);
    }

    @Test
    void testListCurrentBets() throws Exception {
        mockMvc.perform(get("/listCurrentOrders"))
                .andExpect(status().isOk())
                .andExpect(view().name("listCurrentOrders"))
                .andExpect(content().string(containsString("Current Bets")));
    }

    @Test
    void testNewBet() throws Exception {
        mockMvc.perform(get("/bets/new/1.123/456"))
        .andExpect(status().isOk())
        .andExpect(view().name("placeOrders"))
        .andExpect(content().string(containsString("Place Bet")));
    }

    @Test
    void testShowBet() throws Exception {
        Bet bet = mock(Bet.class);
        when(jsonDeserialiser.mapToBetList(any())).thenReturn(Collections.singletonList(bet));

        mockMvc.perform(get("/bets/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("showBet"))
                .andExpect(content().string(containsString("Bet Confirmation")));
    }

    @Test
    void testShowCancelExecutionReport() throws Exception {
        when(reportRepository.findById(1L))
                .thenReturn(java.util.Optional.of(new CancelExecutionReport()));

        mockMvc.perform(get("/cancelExecutionReport/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("cancelExecutionReport"))
                .andExpect(content().string(containsString("Cancel Execution Report")));
    }
}
