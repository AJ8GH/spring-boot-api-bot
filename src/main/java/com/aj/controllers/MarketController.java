package com.aj.controllers;

import com.aj.api.ApiClientService;
import com.aj.deserialisation.JsonDeserialiser;
import com.aj.models.MarketCatalogue;
import com.aj.models.Runner;
import com.aj.repositories.MarketCatalogueRepository;
import com.aj.repositories.RunnerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

@Controller
public class MarketController {
    private final MarketCatalogueRepository marketCatalogueRepository;
    private final RunnerRepository runnerRepository;
    private final ApiClientService apiClient;
    private final JsonDeserialiser jsonDeserialiser;

    public MarketController(
            MarketCatalogueRepository marketCatalogueRepository,
            RunnerRepository runnerRepository,
            JsonDeserialiser jsonDeserialiser,
            ApiClientService apiClient) {

        this.marketCatalogueRepository = marketCatalogueRepository;
        this.runnerRepository = runnerRepository;
        this.jsonDeserialiser = jsonDeserialiser;
        this.apiClient = apiClient;
    }

    @RequestMapping("/listMarketCatalogue/{eventId}")
    public String listMarketCatalogue(@PathVariable("eventId") long eventId,
                                      Model model) throws IOException {

        String response = apiClient.listMarketCatalogue(eventId);

        System.out.println(response);

        List<MarketCatalogue> marketCatalogueList = jsonDeserialiser.mapToMarketCatalogue(response);
        marketCatalogueRepository.saveAll(marketCatalogueList);
        // for (MarketCatalogue market : marketCatalogueList) {
        //     runnerRepository.saveAll(market.getRunners());
        // }
        model.addAttribute("marketCatalogue", marketCatalogueList);

        return "listMarketCatalogue";
    }
}
