package com.aj.enrichment;

import com.aj.models.MarketBook;
import com.aj.models.MarketCatalogue;
import com.aj.models.Runner;
import com.aj.repositories.MarketCatalogueRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class Enricher implements EnrichmentService {

    @Override
    public void enrichMarketBook(MarketBook book, MarketCatalogue catalogue) {
        book.setMarketName(catalogue.getMarketName());
        book.setEvent(catalogue.getEvent());
        book.setEventType(catalogue.getEventType());
        book.setCompetition(catalogue.getCompetition());
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
}
