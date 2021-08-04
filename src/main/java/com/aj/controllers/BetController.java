package com.aj.controllers;

import com.aj.api.ApiClientService;
import com.aj.deserialisation.JsonDeserialiser;
import com.aj.models.Bet;
import com.aj.repositories.BetRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@Controller
@AllArgsConstructor
public class BetController {
    private final ApiClientService apiClient;
    private final JsonDeserialiser jsonDeserialiser;
    private final BetRepository betRepository;

    @RequestMapping("/listCurrentOrders")
    public String listCurrentBets(Model model) throws IOException {
        String response = apiClient.listCurrentOrders();
        List<Bet> bets = jsonDeserialiser.mapToBetList(response);
        betRepository.saveAll(bets);
        model.addAttribute("bets", bets);
        return "listCurrentOrders";
    }

    @RequestMapping("/bets/new/{marketId}/{selectionId}")
    public String newBet(@PathVariable("marketId") String marketId,
                         @PathVariable("selectionId") long selectionId,
                         Model model) {
        model.addAttribute("marketId", marketId);
        model.addAttribute("selectionId", selectionId);
        return "placeOrders";
    }

    @PostMapping("/placeOrders")
    public String placeOrders(Model model,
                              @RequestParam("price") int price,
                              @RequestParam("size") int size,
                              @RequestParam("marketId") String marketId,
                              @RequestParam("selectionId") long selectionId,
                              @RequestParam("side") String side) throws IOException {
        String response = apiClient.placeOrders(marketId, selectionId, side, size, price);
        model.addAttribute("response", response);
        // TODO - redirect to a get route and bet response model
        return "betOutcome";
    }

    @PostMapping("/cancelOrders")
    public String cancelOrders(Model model,
                               @RequestParam("marketId") String marketId,
                               @RequestParam("betId") long betId)
            throws IOException {
        String response = apiClient.cancelOrders(marketId, betId);
        model.addAttribute("response", response);
        // TODO - redirect to a get route and create cancel response model
        return "cancelOutcome";
    }
}
