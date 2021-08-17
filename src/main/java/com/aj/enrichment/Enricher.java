package com.aj.enrichment;

import com.aj.esa.models.MarketChange;
import com.aj.esa.models.ResponseMessage;
import com.aj.esa.models.RunnerChange;
import com.aj.models.MarketBook;
import com.aj.models.MarketCatalogue;
import com.aj.models.Runner;
import com.aj.repositories.MarketCatalogueRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.error.Mark;

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
            if (catalogue == null) continue;

            marketChange.setMarketName(catalogue.getMarketName());
            marketChange.setEventName(catalogue.getEventName());
            marketChange.setEventTypeName(catalogue.getEventTypeName());
            marketChange.setCompetitionName(catalogue.getCompetitionName());

            if (marketChange.getRc() != null) enrichRc(marketChange, catalogue);
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
