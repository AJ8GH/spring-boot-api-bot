package com.aj.enrichment;

import com.aj.domain.esa.ResponseMessage;
import com.aj.domain.Bet;
import com.aj.domain.CancelExecutionReport;
import com.aj.domain.MarketBook;
import com.aj.domain.MarketCatalogue;

public interface EnrichmentService {
    void enrichMarketBook(MarketBook book, Iterable<MarketCatalogue> catalogues);
    void enrichMessage(ResponseMessage message, Iterable<MarketCatalogue> catalogues);
    void enrichBet(Bet bet, MarketCatalogue catalogue);
    void enrichCancelExecution(Bet bet, CancelExecutionReport report);
}
