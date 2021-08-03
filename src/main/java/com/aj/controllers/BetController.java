package com.aj.controllers;

import com.aj.api.ApiClientService;
import com.aj.deserialisation.JsonDeserialiser;
import com.aj.models.Bet;
import com.aj.repositories.BetRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

@Controller
public class BetController {

    private final ApiClientService apiClient;
    private final JsonDeserialiser jsonDeserialiser;
    private final BetRepository betRepository;

    public BetController(
            ApiClientService apiClient,
            JsonDeserialiser jsonDeserialiser,
            BetRepository betRepository) {

        this.apiClient = apiClient;
        this.jsonDeserialiser = jsonDeserialiser;
        this.betRepository = betRepository;
    }

    @RequestMapping("/listCurrentOrders")
    public String listCurrentBets(Model model) throws IOException {
        String response = apiClient.listCurrentOrders();
        List<Bet> bets = jsonDeserialiser.mapToBetList(response);
        betRepository.saveAll(bets);
        model.addAttribute("bets", bets);
        return "listCurrentOrders";
    }

    @RequestMapping("/placeOrders")
    public String placeOrders(Model model) throws IOException {
        return "placeOrders";
    }
}
