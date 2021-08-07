package com.aj.enrichment;

import com.aj.models.MarketBook;
import com.aj.models.MarketCatalogue;
import com.aj.models.Runner;
import com.aj.repositories.MarketCatalogueRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class Enricher implements EnrichmentService {
    private final MarketCatalogueRepository marketCatalogueRepository;

    @Override
    public void enrichMarketBook(MarketBook marketBook) {
        for (MarketCatalogue marketCatalogue : marketCatalogueRepository.findAll()) {
            if (marketBook.getMarketId().equals(marketCatalogue.getMarketId())) {
                marketBook.setMarketName(marketCatalogue.getMarketName());
                if (marketBook.getRunners() != null) {
                    enrichRunners(marketBook, marketCatalogue);
                }
            }
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
}
