package com.aj.enrichment;

import com.aj.domain.bettingtypes.*;
import com.aj.domain.esa.MarketChange;
import com.aj.domain.esa.ResponseMessage;
import com.aj.domain.esa.RunnerChange;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class Enricher {

    public void enrichMarketBook(MarketBook book, Iterable<MarketCatalogue> catalogues) {
        MarketCatalogue catalogue = findById(book.getMarketId(), catalogues);
        if (catalogue == null) return;
        enrichMarket(book, catalogue);
        if (book.getRunners() != null) {
            for (EnrichableRunner runner : book.getRunners()) {
                enrichRunner(runner, catalogue);
            }
        }
    }

    public void enrichMessage(ResponseMessage message, Iterable<MarketCatalogue> catalogues) {
        for (MarketChange marketChange : message.getMc()) {
            MarketCatalogue catalogue = findById(marketChange.getMarketId(), catalogues);
            if (catalogue == null) return;
            enrichMarket(marketChange, catalogue);
            if (marketChange.getRc() != null) {
                for (EnrichableRunner runnerChange : marketChange.getRc()) {
                    enrichRunner(runnerChange, catalogue);
                }
            }
        }
    }

    private void enrichMarket(EnrichableMarket enrichable, MarketCatalogue catalogue) {
        enrichable.setMarketName(catalogue.getMarketName());
        enrichable.setEventName(catalogue.getEventName());
        enrichable.setEventTypeName(catalogue.getEventTypeName());
        enrichable.setCompetitionName(catalogue.getCompetitionName());
    }

    private void enrichRunner(EnrichableRunner runner, MarketCatalogue marketCatalogue) {
        for (Runner catalogueRunner : marketCatalogue.getRunners()) {
            if (runner.getSelectionId() == catalogueRunner.getSelectionId()) {
                runner.setRunnerName(catalogueRunner.getRunnerName());
            }
        }
    }

    public void enrichBet(Bet bet, MarketCatalogue catalogue) {
        bet.setMarketName(catalogue.getMarketName());
        bet.setEventName(catalogue.getEventName());

        for (Runner runner : catalogue.getRunners()) {
            if (Objects.equals(runner.getSelectionId(), bet.getSelectionId())) {
                bet.setRunnerName(runner.getRunnerName());
            }
        }
    }

    public void enrichCancelExecution(Bet bet, CancelExecutionReport report) {
        report.setEventName(bet.getEventName());
        report.setMarketName(bet.getMarketName());
        if (report.getInstructionReports() != null && report.getInstructionReports().size() > 0) {
            report.getInstructionReports().get(0).setRunnerName(bet.getRunnerName());
        }
    }

    private MarketCatalogue findById(String id, Iterable<MarketCatalogue> catalogues) {
        for (MarketCatalogue catalogue : catalogues) {
            if (catalogue.getMarketId().equals(id)) return catalogue;
        }
        return null;
    }
}
