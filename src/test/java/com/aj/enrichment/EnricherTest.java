package com.aj.enrichment;

import com.aj.models.MarketBook;
import com.aj.models.MarketCatalogue;
import com.aj.models.Runner;
import com.aj.repositories.MarketCatalogueRepository;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class EnricherTest {

    @Test
    void testEnrichMarketBook() {
        MarketBook marketBook = new MarketBook();
        marketBook.setMarketId("1.1");
        Runner runner1 = new Runner();
        Runner runner2 = new Runner();
        runner1.setSelectionId(999L);
        runner2.setSelectionId(55L);
        List<Runner> runners = Arrays.asList(runner1, runner2);
        marketBook.setRunners(runners);

        MarketCatalogue marketCatalogue = new MarketCatalogue();

        marketCatalogue.setMarketId("1.1");
        marketCatalogue.setMarketName("New Market Name");

        Runner runner3 = new Runner();
        Runner runner4 = new Runner();
        Runner runner5 = new Runner();
        runner4.setSelectionId(999L);
        runner5.setSelectionId(55L);
        runner4.setRunnerName("999 Runner Name");
        runner5.setRunnerName("55 Runner Name");
        List<Runner> runners2 = Arrays.asList(runner3, runner4, runner5);
        marketCatalogue.setRunners(runners2);

        Enricher enricher = new Enricher();
        enricher.enrichMarketBook(marketBook, marketCatalogue);

        assertEquals("New Market Name", marketBook.getMarketName());
        assertEquals("999 Runner Name", runner1.getRunnerName());
        assertEquals("55 Runner Name", runner2.getRunnerName());
    }
}
