package com.aj.enrichment;

import com.aj.esa.models.ResponseMessage;
import com.aj.models.Bet;
import com.aj.models.MarketBook;
import com.aj.models.MarketCatalogue;

public interface EnrichmentService {
    void enrichMarketBook(MarketBook book, Iterable<MarketCatalogue> catalogues);
    void enrichMessage(ResponseMessage message, Iterable<MarketCatalogue> catalogues);
    void enrichBet(Bet bet, MarketCatalogue catalogue);
}
