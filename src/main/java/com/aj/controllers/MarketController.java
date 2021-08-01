package com.aj.controllers;

import com.aj.api.ApiClientService;
import com.aj.deserialisation.JsonDeserialiser;
import com.aj.models.Market;
import com.aj.repositories.MarketRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@Controller
public class MarketController {
    private final MarketRepository marketRepository;
    private final ApiClientService apiClient;
    private final JsonDeserialiser jsonDeserialiser;

    public MarketController(
            MarketRepository marketRepository,
            JsonDeserialiser jsonDeserialiser,
            ApiClientService apiClient) {

        this.marketRepository = marketRepository;
        this.jsonDeserialiser = jsonDeserialiser;
        this.apiClient = apiClient;
    }

    @RequestMapping("/listMarketCatalogue/{eventId}")
    public String listMarketCatalogue(@PathVariable("eventId") long eventId,
                                      Model model) throws IOException {

        // String response = apiClient.listMarketCatalogue(eventId);
        // List<Market> markets = jsonDeserialiser.mapToMarketList(response);
        // marketRepository.saveAll(markets);
        // model.addAttribute("markets", markets);

        return "listMarketCatalogue";
    }
}
