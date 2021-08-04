package com.aj.controllers;

import com.aj.api.ApiClientService;
import com.aj.deserialisation.JsonDeserialiser;
import com.aj.models.MarketBook;
import com.aj.models.MarketCatalogue;
import com.aj.models.Runner;
import com.aj.repositories.MarketCatalogueRepository;
import com.aj.repositories.RunnerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

@Controller
@AllArgsConstructor
public class MarketController {
    private final MarketCatalogueRepository marketCatalogueRepository;
    private final RunnerRepository runnerRepository;
    private final ApiClientService apiClient;
    private final JsonDeserialiser jsonDeserialiser;

    @RequestMapping("/listMarketCatalogue/{eventId}")
    public String listMarketCatalogue(@PathVariable("eventId") long eventId,
                                      Model model) throws IOException {

        String response = apiClient.listMarketCatalogue(eventId);
        List<MarketCatalogue> marketCatalogueList = jsonDeserialiser.mapToMarketCatalogue(response);
        marketCatalogueRepository.saveAll(marketCatalogueList);
        model.addAttribute("marketCatalogue", marketCatalogueList);
        return "listMarketCatalogue";
    }

    @RequestMapping("/listMarketBook/{marketId}")
    public String listMarketBook(@PathVariable("marketId") String marketId,
                                 Model model) throws IOException {
        String response = apiClient.listMarketBook(marketId);
        MarketBook marketBook = jsonDeserialiser.mapToMarketBook(response);
        model.addAttribute("marketBook", marketBook);
        return "listMarketBook";
    }
}
