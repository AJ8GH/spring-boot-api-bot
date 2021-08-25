package com.aj.controllers;

import com.aj.api.ApiClient;
import com.aj.deserialisation.JsonDeserialiser;
import com.aj.enrichment.Enricher;
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
    private final String MARKETS_LIST_CATALOGUE = "markets/listCatalogue";
    private final String MARKETS_LIST_BOOK = "markets/listBook";
    private final String MARKETS_SUBSCRIPTIONS_NEW = "markets/subscriptions/new";
    private final String MARKETS_SUBSCRIBE = "markets/subscribe";
    private final String MARKETS_SUBSCRIPTIONS_SHOW = "markets/subscriptions/show";
    private final String MARKETS_SUBSCRIPTIONS_DELETE = "markets/subscriptions/delete";

    private final ApiClient apiClient;
    private final JsonDeserialiser jsonDeserialiser;
    private final Enricher enricher;
    private final EsaClient esaClient;
    private final MarketSubscriptionCache cache;
    private final MarketCatalogueRepository marketCatalogueRepository;
    private int heartbeatCount;

    public MarketController(ApiClient apiClient,
                            JsonDeserialiser jsonDeserialiser,
                            Enricher enricher,
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

        @RequestMapping(INDEX_ROUTE + MARKETS_LIST_CATALOGUE + "/{eventId}")
    public String listMarketCatalogue(@PathVariable("eventId") String eventId,
                                      Model model) throws IOException {
        if (isNotLoggedIn(apiClient.getUserSession())) return "redirect:/login";

        String response = apiClient.catalogueByEvent(eventId);
        List<MarketCatalogue> marketCatalogueList = jsonDeserialiser
                .mapToMarketCatalogue(response);
        marketCatalogueRepository.saveAll(marketCatalogueList);
        model.addAttribute("marketCatalogue", marketCatalogueList);
        return MARKETS_LIST_CATALOGUE;
    }

    @RequestMapping(INDEX_ROUTE + MARKETS_LIST_BOOK + "/{marketId}")
    public String listMarketBook(@PathVariable("marketId") String marketId,
                                 Model model) throws IOException {
        if (isNotLoggedIn(apiClient.getUserSession())) return "redirect:/login";

        String marketBookResponse = apiClient.listMarketBook(marketId);
        MarketBook marketBook = jsonDeserialiser.mapToMarketBook(marketBookResponse);

        enricher.enrichMarketBook(marketBook, marketCatalogueRepository.findAll());
        model.addAttribute("marketBook", marketBook);

        return MARKETS_LIST_BOOK;
    }

    @RequestMapping(INDEX_ROUTE + MARKETS_SUBSCRIPTIONS_NEW + "/{marketId}")
    public String newMarketSubscription(@PathVariable("marketId") String marketId,
                                     Model model) {
        if (isNotLoggedIn(apiClient.getUserSession())) return REDIRECT + LOGIN;

        model.addAttribute("marketId", marketId);
        return MARKETS_SUBSCRIPTIONS_NEW;
    }

    @PostMapping(INDEX_ROUTE + MARKETS_SUBSCRIBE + "/{marketId}")
    public String showMarketSubscription(@PathVariable("marketId") String marketId,
                                     @RequestParam("timeout") int timeout,
                                     Model model) throws IOException {
        if (isNotLoggedIn(apiClient.getUserSession())) return REDIRECT + LOGIN;

        esaClient.setUserSession(UserSession.getCurrentSession());
        esaClient.connect(timeout);
        esaClient.authenticate();
        esaClient.subscribeToMarkets(marketId);

        String response2 = esaClient.pollStream();
        ResponseMessage message = jsonDeserialiser
                .mapToObject(response2, ResponseMessage.class);

        if (message.getMc() != null) {
            enricher.enrichMessage(message, marketCatalogueRepository.findAll());
        }

        cache.addMessage(message);
        model.addAttribute("snapshot", message);

        return REDIRECT + MARKETS_SUBSCRIPTIONS_SHOW + INDEX_ROUTE + marketId;
    }

    @RequestMapping(INDEX_ROUTE + MARKETS_SUBSCRIPTIONS_SHOW + "/{marketId}")
    public String marketChange(Model model) throws IOException {
        if (isNotLoggedIn(apiClient.getUserSession())) return REDIRECT + LOGIN;

        incrementHeartbeatCount();
        if (isTimedOut()) {
            closeConnection();
            return REDIRECT;
        }

        String response = esaClient.pollStream();
        ResponseMessage message = jsonDeserialiser.mapToObject(response, ResponseMessage.class);
        if (message.getMc() != null) {
            enricher.enrichMessage(message, marketCatalogueRepository.findAll());
            cache.addMessage(message);
        }

        ResponseMessage currentMarketUpdate = cache.getMessage(message.getId());
        model.addAttribute("responseMessage", currentMarketUpdate);

        return MARKETS_SUBSCRIPTIONS_SHOW;
    }

    @PostMapping(INDEX_ROUTE + MARKETS_SUBSCRIPTIONS_DELETE + "/{marketId}")
    public String disconnect(@PathVariable("marketId") String marketId)
            throws IOException {
        if (isNotLoggedIn(apiClient.getUserSession())) return REDIRECT + LOGIN;

        closeConnection();
        return REDIRECT + MARKETS_LIST_BOOK + INDEX_ROUTE + marketId;
    }

    private boolean isTimedOut() throws IOException {
        return esaClient.getTimeout() <= (heartbeatCount - 1) * 500;
    }

    private void closeConnection() throws IOException {
        esaClient.close();
        heartbeatCount = 0  ;
    }

    private void incrementHeartbeatCount() {
        heartbeatCount += 1;
    }
}
