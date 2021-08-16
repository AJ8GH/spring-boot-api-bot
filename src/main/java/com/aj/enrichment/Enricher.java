package com.aj.enrichment;

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

    private MarketCatalogue findById(String id, Iterable<MarketCatalogue> catalogues) {
        for (MarketCatalogue catalogue : catalogues) {
            if (catalogue.getMarketId().equals(id)) return catalogue;
        }
        return null;
    }
}
