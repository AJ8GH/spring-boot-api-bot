package com.aj.controllers;

import com.aj.api.ApiClientService;
import com.aj.deserialisation.DeserialisationService;
import com.aj.enrichment.EnrichmentService;
import com.aj.models.Bet;
import com.aj.models.CancelExecutionReport;

import com.aj.models.MarketCatalogue;
import com.aj.repositories.BetRepository;
import com.aj.repositories.CancelExecutionReportRepository;
import com.aj.repositories.MarketCatalogueRepository;
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
    private final ApiClientService apiClient;
    private final BetRepository betRepository;
    private final DeserialisationService jsonDeserialiser;
    private final EnrichmentService enricher;
    private final CancelExecutionReportRepository reportRepository;

    @RequestMapping("/listCurrentOrders")
    public String listCurrentBets(Model model) throws IOException {
        if (isNotLoggedIn(apiClient.getUserSession())) return "redirect:/login";

        String response = apiClient.listCurrentOrders();
        List<Bet> bets = jsonDeserialiser.mapToBetList(response);
        for (Bet bet : bets) enrichBet(bet);
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
        if (isNotLoggedIn(apiClient.getUserSession())) return "redirect:/login";

        return "placeOrders";
    }

    @PostMapping("/placeOrders")
    public String placeOrders(Model model,
                              @RequestParam("price") double price,
                              @RequestParam("size") double size,
                              @RequestParam("marketId") String marketId,
                              @RequestParam("selectionId") long selectionId,
                              @RequestParam("side") String side) throws IOException {
        if (isNotLoggedIn(apiClient.getUserSession())) return "redirect:/login";

        String response = apiClient.placeOrders(marketId, selectionId, side, size, price);
        Bet bet = jsonDeserialiser.mapToObject(response, Bet.class);
        return ("redirect:/bets/" + bet.getBetId());
    }

    @RequestMapping("/bets/{betId}")
    public String showBet(Model model, @PathVariable("betId") String betId)
            throws IOException {
        if (isNotLoggedIn(apiClient.getUserSession())) return "redirect:/login";

        String response = apiClient.listCurrentOrders(betId);
        Bet bet = jsonDeserialiser.mapToBetList(response).get(0);
        enrichBet(bet);

        model.addAttribute("bet", bet);
        return "showBet";
    }

    @PostMapping("/cancelOrders")
    public String cancelOrders(@RequestParam("marketId") String marketId,
                               @RequestParam("betId") long betId,
                               HttpServletRequest request)
            throws IOException {
        if (isNotLoggedIn(apiClient.getUserSession())) return "redirect:/login";

        String response = apiClient.cancelOrders(marketId, betId);
        CancelExecutionReport report = jsonDeserialiser
                .mapToObject(response, CancelExecutionReport.class);
        reportRepository.save(report);
        return "redirect:/cancelExecutionReport/" + report.getId();
    }

    @RequestMapping("/cancelExecutionReport/{reportId}")
    public String showCancelExecutionReport(
            Model model, @PathVariable("reportId") long reportId) {
        if (isNotLoggedIn(apiClient.getUserSession())) return "redirect:/login";

        Optional<CancelExecutionReport> report = reportRepository.findById(reportId);
        model.addAttribute("report", report);
        return "cancelExecutionReport";
    }

    private void enrichBet(Bet bet) throws IOException {
        String response = apiClient
                .listMarketCatalogue("marketIds", bet.getMarketId());

        List<MarketCatalogue> catalogues = jsonDeserialiser
                .mapToMarketCatalogue(response);

        enricher.enrichBet(bet, catalogues.get(0));
    }
}
