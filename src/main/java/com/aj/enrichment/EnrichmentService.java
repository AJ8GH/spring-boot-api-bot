package com.aj.enrichment;

import com.aj.models.MarketBook;
import com.aj.models.MarketCatalogue;

public interface EnrichmentService {
    void enrichMarketBook(MarketBook book, Iterable<MarketCatalogue> catalogues);
}
