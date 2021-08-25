package com.aj.controllers;

import com.aj.api.ApiClient;
import com.aj.deserialisation.JsonDeserialiser;
import com.aj.domain.bettingtypes.Bet;
import com.aj.domain.bettingtypes.CancelExecutionReport;

import com.aj.domain.bettingtypes.MarketCatalogue;
import com.aj.enrichment.Enricher;
import com.aj.repositories.BetRepository;
import com.aj.repositories.CancelExecutionReportRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class BetController extends AbstractController {
    private final String BETS_LIST_VIEW = "bets/list";
    private final String BETS_NEW_VIEW = "bets/new";
    private final String BETS_SHOW_VIEW = "bets/show";
    private final String BETS_DELETE_VIEW = "bets/delete";

    private final ApiClient apiClient;
    private final JsonDeserialiser jsonDeserialiser;
    private final Enricher enricher;
    private final BetRepository betRepository;
    private final CancelExecutionReportRepository reportRepository;

    @RequestMapping(INDEX_ROUTE + BETS_LIST_VIEW)
    public String listCurrentBets(Model model) throws IOException {
        if (isNotLoggedIn(apiClient.getUserSession())) return REDIRECT + LOGIN;

        String response = apiClient.listCurrentOrders();
        List<Bet> bets = jsonDeserialiser.mapToBetList(response);
        for (Bet bet : bets) enrichBet(bet);
        betRepository.saveAll(bets);
        model.addAttribute("bets", bets);
        return BETS_LIST_VIEW;
    }

    @RequestMapping(INDEX_ROUTE + BETS_NEW_VIEW + "/{marketId}/{selectionId}")
    public String newBet(@PathVariable("marketId") String marketId,
                         @PathVariable("selectionId") long selectionId,
                         Model model) {
        model.addAttribute("marketId", marketId);
        model.addAttribute("selectionId", selectionId);
        if (isNotLoggedIn(apiClient.getUserSession())) return REDIRECT + LOGIN;

        return BETS_NEW_VIEW;
    }

    @PostMapping(INDEX_ROUTE + BETS_NEW_VIEW)
    public String placeOrders(Model model,
                              @RequestParam("price") double price,
                              @RequestParam("size") double size,
                              @RequestParam("marketId") String marketId,
                              @RequestParam("selectionId") long selectionId,
                              @RequestParam("side") String side) throws IOException {
        if (isNotLoggedIn(apiClient.getUserSession())) return REDIRECT + LOGIN;

        String response = apiClient.placeOrders(marketId, selectionId, side, size, price);
        Bet bet = jsonDeserialiser.mapToObject(response, Bet.class);
        return (REDIRECT + BETS_SHOW_VIEW + INDEX_ROUTE + bet.getBetId());
    }

    @RequestMapping( INDEX_ROUTE + BETS_SHOW_VIEW + "/{betId}")
    public String showBet(Model model, @PathVariable("betId") String betId)
            throws IOException {
        if (isNotLoggedIn(apiClient.getUserSession())) return REDIRECT + LOGIN;

        String response = apiClient.listCurrentOrders(betId);
        Bet bet = jsonDeserialiser.mapToBetList(response).get(0);
        enrichBet(bet);
        betRepository.save(bet);

        model.addAttribute("bet", bet);
        return BETS_SHOW_VIEW;
    }

    @PostMapping(INDEX_ROUTE + BETS_DELETE_VIEW)
    public String cancelOrders(@RequestParam("marketId") String marketId,
                               @RequestParam("betId") String betId,
                               HttpServletRequest request) throws IOException {
        if (isNotLoggedIn(apiClient.getUserSession())) return REDIRECT + LOGIN;

        String response = apiClient.cancelOrders(marketId, betId);
        CancelExecutionReport report = jsonDeserialiser
                .mapToObject(response, CancelExecutionReport.class);
        reportRepository.save(report);
        return REDIRECT + BETS_DELETE_VIEW + INDEX_ROUTE + report.getId();
    }

    @RequestMapping(INDEX_ROUTE + BETS_DELETE_VIEW + "/{reportId}")
    public String showCancelExecutionReport(
            Model model, @PathVariable("reportId") long reportId) {
        if (isNotLoggedIn(apiClient.getUserSession())) return REDIRECT + LOGIN;

        Optional<CancelExecutionReport> report = reportRepository.findById(reportId);
        String betId = report.get().getInstructionReports().get(0).getBetId();
        Bet bet = Bet.findByBetId(betId, betRepository.findAll());
        enricher.enrichCancelExecution(bet, report.get());
        model.addAttribute("report", report);
        return BETS_DELETE_VIEW;
    }

    private void enrichBet(Bet bet) throws IOException {
        String response = apiClient
                .catalogueByMarket(bet.getMarketId());

        List<MarketCatalogue> catalogues = jsonDeserialiser
                .mapToMarketCatalogue(response);

        enricher.enrichBet(bet, catalogues.get(0));
    }
}
