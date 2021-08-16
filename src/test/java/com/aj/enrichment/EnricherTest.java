package com.aj.enrichment;

import com.aj.models.*;
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
        Runner runner1 = new Runner();
        Runner runner2 = new Runner();
        runner1.setSelectionId(999L);
        runner2.setSelectionId(55L);
        List<Runner> runners = Arrays.asList(runner1, runner2);

        MarketBook marketBook = MarketBook.builder()
                .marketId("1.1")
                .runners(runners)
                .build();

        Event event = new Event();
        event.setName("eventName");
        EventType eventType = new EventType();
        eventType.setName("eventTypeName");
        Competition competition = new Competition();
        competition.setName("competitionName");

        Runner runner3 = new Runner();
        Runner runner4 = new Runner();
        Runner runner5 = new Runner();
        runner4.setSelectionId(999L);
        runner5.setSelectionId(55L);
        runner4.setRunnerName("999 Runner Name");
        runner5.setRunnerName("55 Runner Name");
        List<Runner> runners2 = Arrays.asList(runner3, runner4, runner5);

        MarketCatalogue marketCatalogue = MarketCatalogue.builder()
                .marketId("1.1")
                .marketName("New Market Name")
                .runners(runners2)
                .event(event)
                .eventType(eventType)
                .competition(competition)
                .build();

        Enricher enricher = new Enricher();
        enricher.enrichMarketBook(marketBook, marketCatalogue);

        assertEquals("New Market Name", marketBook.getMarketName());
        assertEquals("999 Runner Name", runner1.getRunnerName());
        assertEquals("55 Runner Name", runner2.getRunnerName());
        assertEquals("eventName", marketBook.getEvent().getName());
        assertEquals("eventTypeName", marketBook.getEventType().getName());
        assertEquals("competitionName", marketBook.getCompetition().getName());
    }
}
