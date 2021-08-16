package com.aj.controllers;

import com.aj.api.ApiClientService;
import com.aj.deserialisation.DeserialisationService;
import com.aj.enrichment.EnrichmentService;
import com.aj.esa.EsaClient;
import com.aj.models.MarketBook;
import com.aj.models.MarketCatalogue;
import com.aj.models.UserSession;
import com.aj.repositories.MarketCatalogueRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@Controller
@AllArgsConstructor
public class MarketController extends AbstractController {
    private final ApiClientService apiClient;
    private final DeserialisationService jsonDeserialiser;
    private final EnrichmentService enricher;
    private final MarketCatalogueRepository marketCatalogueRepository;
    private final EsaClient esaClient;

    @RequestMapping("/listMarketCatalogue/{eventId}")
    public String listMarketCatalogue(@PathVariable("eventId") String eventId,
                                      Model model) throws IOException {
        if (isNotLoggedIn(apiClient.getUserSession())) return "redirect:/login";

        String response = apiClient.listMarketCatalogue("eventIds", eventId);
        List<MarketCatalogue> marketCatalogueList = jsonDeserialiser.mapToMarketCatalogue(response);
        marketCatalogueRepository.saveAll(marketCatalogueList);
        model.addAttribute("marketCatalogue", marketCatalogueList);
        return "listMarketCatalogue";
    }

    @RequestMapping("/listMarketBook/{marketId}")
    public String listMarketBook(@PathVariable("marketId") String marketId,
                                 Model model) throws IOException {
        if (isNotLoggedIn(apiClient.getUserSession())) return "redirect:/login";

        String marketBookResponse = apiClient.listMarketBook(marketId);
        MarketBook marketBook = jsonDeserialiser.mapToMarketBook(marketBookResponse);

        enricher.enrichMarketBook(marketBook, marketCatalogueRepository.findAll());
        model.addAttribute("marketBook", marketBook);

        return "listMarketBook";
    }

    @RequestMapping("/marketSubscription/new/{marketId}")
    public String newMarketSubscription(@PathVariable("marketId") String marketId,
                                     Model model) {
        model.addAttribute("marketId", marketId);
        return "newMarketSubscription";
    }

    @RequestMapping("/marketSubscription/{marketId}")
    public String showMarketSubscription(@PathVariable("marketId") String marketId,
                                     @RequestParam("timeout") int timeout,
                                     Model model) throws IOException {
        esaClient.setUserSession(UserSession.getCurrentSession());
        esaClient.connect(timeout);
        esaClient.authenticate();
        String snapShot = esaClient.subscribeToMarkets(marketId);
        model.addAttribute("snapshot", snapShot);
        return "redirect:/marketChange";
    }

    @RequestMapping("/marketChange")
    public String marketChange(Model model) throws IOException {
        String marketChange = esaClient.getLatest();
        model.addAttribute("marketChange", marketChange);
        return "marketSubscription";
    }
}
