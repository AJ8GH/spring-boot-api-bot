package com.aj.controllers;

import com.aj.api.ApiClientService;
import com.aj.deserialisation.DeserialisationService;
import com.aj.enrichment.EnrichmentService;
import com.aj.esa.EsaClient;
import com.aj.esa.cache.MarketSubscriptionCache;
import com.aj.domain.esa.ResponseMessage;
import com.aj.domain.bettingtypes.MarketBook;
import com.aj.domain.bettingtypes.MarketCatalogue;
import com.aj.domain.bettingtypes.UserSession;
import com.aj.repositories.MarketCatalogueRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@Controller
public class MarketController extends AbstractController {
    private final ApiClientService apiClient;
    private final DeserialisationService jsonDeserialiser;
    private final EnrichmentService enricher;
    private final MarketCatalogueRepository marketCatalogueRepository;
    private final MarketSubscriptionCache cache;
    private final EsaClient esaClient;
    private int heartbeatCount;

    public MarketController(ApiClientService apiClient,
                            DeserialisationService jsonDeserialiser,
                            EnrichmentService enricher,
                            MarketCatalogueRepository marketCatalogueRepository,
                            MarketSubscriptionCache cache,
                            EsaClient esaClient) {
        this.apiClient = apiClient;
        this.jsonDeserialiser = jsonDeserialiser;
        this.enricher = enricher;
        this.marketCatalogueRepository = marketCatalogueRepository;
        this.cache = cache;
        this.esaClient = esaClient;
    }

    @RequestMapping("/markets/listCatalogue/{eventId}")
    public String listMarketCatalogue(@PathVariable("eventId") String eventId,
                                      Model model) throws IOException {
        if (isNotLoggedIn(apiClient.getUserSession())) return "redirect:/login";

        String response = apiClient.catalogueByEvent(eventId);
        List<MarketCatalogue> marketCatalogueList = jsonDeserialiser.mapToMarketCatalogue(response);
        marketCatalogueRepository.saveAll(marketCatalogueList);
        model.addAttribute("marketCatalogue", marketCatalogueList);
        return "markets/listCatalogue";
    }

    @RequestMapping("/markets/listBook/{marketId}")
    public String listMarketBook(@PathVariable("marketId") String marketId,
                                 Model model) throws IOException {
        if (isNotLoggedIn(apiClient.getUserSession())) return "redirect:/login";

        String marketBookResponse = apiClient.listMarketBook(marketId);
        MarketBook marketBook = jsonDeserialiser.mapToMarketBook(marketBookResponse);

        enricher.enrichMarketBook(marketBook, marketCatalogueRepository.findAll());
        model.addAttribute("marketBook", marketBook);

        return "markets/listBook";
    }

    @RequestMapping("/markets/subscriptions/new/{marketId}")
    public String newMarketSubscription(@PathVariable("marketId") String marketId,
                                     Model model) {
        model.addAttribute("marketId", marketId);
        return "markets/subscriptions/new";
    }

    @PostMapping("/markets/subscribe/{marketId}")
    public String showMarketSubscription(@PathVariable("marketId") String marketId,
                                     @RequestParam("timeout") int timeout,
                                     Model model) throws IOException {

        esaClient.setUserSession(UserSession.getCurrentSession());
        esaClient.connect(timeout);
        esaClient.authenticate();
        esaClient.subscribeToMarkets(marketId);

        String response2 = esaClient.getLatest();
        ResponseMessage message = jsonDeserialiser.mapToObject(response2, ResponseMessage.class);

        if (message.getMc() != null) {
            enricher.enrichMessage(message, marketCatalogueRepository.findAll());
        }

        cache.addMessage(message);
        model.addAttribute("snapshot", message);

        return "redirect:/markets/subscriptions/show/" + marketId;
    }

    @RequestMapping("/markets/subscriptions/show/{marketId}")
    public String marketChange(Model model) throws IOException {
        incrementHeartbeatCount();

        if (isTimedOut()) {
            closeConnection();
            return "redirect:/";
        }

        String response = esaClient.getLatest();
        ResponseMessage message = jsonDeserialiser.mapToObject(response, ResponseMessage.class);
        if (message.getCt().equals("SUB_IMAGE")) cache.addMessage(message);

        ResponseMessage currentMarketUpdate = cache.getMessage(message.getId());
        model.addAttribute("responseMessage", currentMarketUpdate);

        return "markets/subscriptions/show";
    }

    @PostMapping("/markets/subscriptions/delete/{marketId}")
    public String disconnect(@PathVariable("marketId") String marketId) throws IOException {
        closeConnection();
        return "redirect:/markets/listBook/" + marketId;
    }

    private boolean isTimedOut() throws IOException {
        return esaClient.getTimeout() <= (heartbeatCount - 1) * 5000;
    }

    private void closeConnection() throws IOException {
        esaClient.close();
        heartbeatCount = 0  ;
    }

    private void incrementHeartbeatCount() {
        heartbeatCount += 1;
    }
}
