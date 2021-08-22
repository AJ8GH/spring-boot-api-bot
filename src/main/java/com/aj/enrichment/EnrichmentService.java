package com.aj.enrichment;

import com.aj.domain.esa.ResponseMessage;
import com.aj.domain.bettingtypes.Bet;
import com.aj.domain.bettingtypes.CancelExecutionReport;
import com.aj.domain.bettingtypes.MarketBook;
import com.aj.domain.bettingtypes.MarketCatalogue;

public interface EnrichmentService {
    void enrichMarketBook(MarketBook book, Iterable<MarketCatalogue> catalogues);
    void enrichMessage(ResponseMessage message, Iterable<MarketCatalogue> catalogues);
    void enrichBet(Bet bet, MarketCatalogue catalogue);
    void enrichCancelExecution(Bet bet, CancelExecutionReport report);
}
