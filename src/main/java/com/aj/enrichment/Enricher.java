package com.aj.enrichment;

import com.aj.domain.bettingtypes.*;
import com.aj.domain.esa.MarketChange;
import com.aj.domain.esa.ResponseMessage;
import com.aj.domain.esa.RunnerChange;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class Enricher implements EnrichmentService {

    @Override
    public void enrichMarketBook(MarketBook book, Iterable<MarketCatalogue> catalogues) {
        MarketCatalogue catalogue = findById(book.getMarketId(), catalogues);
        if (catalogue == null) return;

        book.setMarketName(catalogue.getMarketName());
        book.setEventName(catalogue.getEventName());
        book.setEventTypeName(catalogue.getEventTypeName());
        book.setCompetitionName(catalogue.getCompetitionName());

        if (book.getRunners() != null) enrichRunners(book, catalogue);
    }

    public void enrichMessage(ResponseMessage message, Iterable<MarketCatalogue> catalogues) {
        for (MarketChange marketChange : message.getMc()) {
            MarketCatalogue catalogue = findById(marketChange.getMarketId(), catalogues);
            if (catalogue == null) return;

            marketChange.setMarketName(catalogue.getMarketName());
            marketChange.setEventName(catalogue.getEventName());
            marketChange.setEventTypeName(catalogue.getEventTypeName());
            marketChange.setCompetitionName(catalogue.getCompetitionName());

            if (marketChange.getRc() != null) enrichRc(marketChange, catalogue);
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

    private void enrichRunners(
            MarketBook marketBook, MarketCatalogue marketCatalogue) {
        for (Runner bookRunner : marketBook.getRunners()) {
            for (Runner catalogueRunner : marketCatalogue.getRunners()) {
                if (bookRunner.getSelectionId().equals(catalogueRunner.getSelectionId())) {
                    bookRunner.setRunnerName(catalogueRunner.getRunnerName());
                }
            }
        }
    }

    private void enrichRc(
            MarketChange marketChange, MarketCatalogue catalogue) {
        for (RunnerChange runnerChange : marketChange.getRc()) {
            for (Runner runner : catalogue.getRunners()) {
                if (runnerChange.getSelectionId().equals(runner.getSelectionId())) {
                    runnerChange.setRunnerName(runner.getRunnerName());
                }
            }
        }
    }

    private MarketCatalogue findById(String id, Iterable<MarketCatalogue> catalogues) {
        for (MarketCatalogue catalogue : catalogues) {
            if (catalogue.getMarketId().equals(id)) return catalogue;
        }
        return null;
    }
}
