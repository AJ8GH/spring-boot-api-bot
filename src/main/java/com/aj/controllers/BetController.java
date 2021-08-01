package com.aj.controllers;

import com.aj.api.ApiClientService;
import com.aj.models.Bet;
import com.aj.repositories.BetRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Controller
public class BetController {

    private final ApiClientService apiClient;
    private final ObjectMapper objectMapper;
    private final BetRepository betRepository;

    public BetController(
            ApiClientService apiClient,
            ObjectMapper objectMapper,
            BetRepository betRepository) {

        this.apiClient = apiClient;
        this.objectMapper = objectMapper;
        this.betRepository = betRepository;
    }

    @RequestMapping("/listCurrentOrders")
    public String listCurrentBets(Model model) throws IOException {
        String response = apiClient.listCurrentOrders();
        String data = String.join("", response.split("\\[|\\]")[1]);
        String jsonArray = "[" + data + "]";
        List<Bet> bets = Arrays.asList(objectMapper.readValue(jsonArray, Bet[].class));
        betRepository.saveAll(bets);
        model.addAttribute("bets", bets);
        return "listCurrentOrders";
    }
}
