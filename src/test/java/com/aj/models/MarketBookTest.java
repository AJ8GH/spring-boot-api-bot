package com.aj.models;

import com.aj.repositories.MarketCatalogueRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MarketBookTest {

    @Test
    void testMarketBook() {
        List<Runner> runners = new ArrayList<>();
        MarketBook marketBook = new MarketBook(9L, "1.23", "name",
                "status", true, 10, 0.0, 1.1, runners);

        assertEquals(9L, marketBook.getId());
        assertEquals("1.23", marketBook.getMarketId());
        assertEquals("status", marketBook.getStatus());
        assertEquals(true, marketBook.getComplete());
        assertEquals(0.0, marketBook.getTotalMatched());
        assertEquals(1.1, marketBook.getTotalAvailable());
        assertEquals(10, marketBook.getNumberOfActiveRunners());
        assertEquals(runners, marketBook.getRunners());
    }

    @Test
    void testToString() {
        List<Runner> runners = new ArrayList<>();
        MarketBook marketBook = new MarketBook(9L, "1.23", "name",
                "status", true, 10, 0.0, 1.1, runners);

        String toString = marketBook.toString();

        assertTrue(toString.contains(marketBook.getMarketId()));
        assertTrue(toString.contains(marketBook.getStatus()));
        assertTrue(toString.contains(marketBook.getMarketName()));
        assertTrue(toString.contains(marketBook.getComplete().toString()));
        assertTrue(toString.contains(marketBook.getId().toString()));
        assertTrue(toString.contains(marketBook.getTotalMatched().toString()));
        assertTrue(toString.contains(marketBook.getNumberOfActiveRunners().toString()));
        assertTrue(toString.contains(marketBook.getTotalAvailable().toString()));
    }

    @Test
    void testEnrichMarket() {
        MarketBook marketBook = new MarketBook();
        marketBook.setMarketId("1.1");

        MarketCatalogue market1 = new MarketCatalogue();
        MarketCatalogue market2 = new MarketCatalogue();
        MarketCatalogue market3 = new MarketCatalogue();
        market2.setMarketId("1.1");
        market2.setMarketName("New Market Name");
        List<MarketCatalogue> marketList = Arrays.asList(market1, market2, market3);

        MarketCatalogueRepository repository = mock(MarketCatalogueRepository.class);
        when(repository.findAll()).thenReturn(marketList);
        MarketBook.enrich(marketBook, repository);

        assertEquals("New Market Name", marketBook.getMarketName());
    }

    @Test
    void testEnrichMarketWithRunners() {
        MarketBook marketBook = new MarketBook();
        marketBook.setMarketId("1.1");
        Runner runner1 = new Runner();
        Runner runner2 = new Runner();
        runner1.setSelectionId(999L);
        runner2.setSelectionId(55L);
        List<Runner> runners = Arrays.asList(runner1, runner2);
        marketBook.setRunners(runners);

        MarketCatalogue market1 = new MarketCatalogue();
        MarketCatalogue market2 = new MarketCatalogue();
        MarketCatalogue market3 = new MarketCatalogue();
        List<MarketCatalogue> marketList = Arrays.asList(market1, market2, market3);
        market2.setMarketId("1.1");

        Runner runner3 = new Runner();
        Runner runner4 = new Runner();
        Runner runner5 = new Runner();
        runner4.setSelectionId(999L);
        runner4.setRunnerName("999 Runner Name");
        runner5.setSelectionId(55L);
        runner5.setRunnerName("55 Runner Name");
        List<Runner> runners2 = Arrays.asList(runner3, runner4, runner5);
        market2.setRunners(runners2);

        MarketCatalogueRepository repository = mock(MarketCatalogueRepository.class);
        when(repository.findAll()).thenReturn(marketList);
        MarketBook.enrich(marketBook, repository);

        assertEquals("999 Runner Name", marketBook.getRunners().get(0).getRunnerName());
        assertEquals("55 Runner Name", marketBook.getRunners().get(1).getRunnerName());
    }
}
