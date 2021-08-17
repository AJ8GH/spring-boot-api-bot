package com.aj.enrichment;

import com.aj.esa.models.MarketChange;
import com.aj.esa.models.ResponseMessage;
import com.aj.esa.models.RunnerChange;
import com.aj.models.*;
import com.aj.repositories.MarketCatalogueRepository;
import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.error.Mark;

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

        Runner runner3 = new Runner();
        Runner runner4 = new Runner();
        Runner runner5 = new Runner();
        runner4.setSelectionId(999L);
        runner5.setSelectionId(55L);
        runner4.setRunnerName("999 Runner");
        runner5.setRunnerName("55 Runner");
        List<Runner> runners2 = Arrays.asList(runner3, runner4, runner5);

        MarketCatalogue marketCatalogue = MarketCatalogue.builder()
                .marketId("1.1")
                .marketName("New Market")
                .runners(runners2)
                .eventName("event")
                .eventTypeName("eventType")
                .competitionName("competition")
                .build();

        Enricher enricher = new Enricher();
        enricher.enrichMarketBook(marketBook, List.of(marketCatalogue));

        assertEquals("New Market", marketBook.getMarketName());
        assertEquals("999 Runner", runner1.getRunnerName());
        assertEquals("55 Runner", runner2.getRunnerName());
        assertEquals("event", marketBook.getEventName());
        assertEquals("eventType", marketBook.getEventTypeName());
        assertEquals("competition", marketBook.getCompetitionName());
    }

    @Test
    void enrichMessage() {
        RunnerChange runnerChange1 = RunnerChange.builder()
                .selectionId(999L)
                .build();

        RunnerChange runnerChange2 = RunnerChange.builder()
                .selectionId(55L)
                .build();

        MarketChange marketChange = MarketChange.builder()
                .rc(List.of(runnerChange1, runnerChange2))
                .marketId("1.1")
                .build();

        ResponseMessage message = ResponseMessage.builder()
                .mc(List.of(marketChange))
                .build();

        Runner runner3 = new Runner();
        Runner runner4 = new Runner();
        Runner runner5 = new Runner();
        runner4.setSelectionId(999L);
        runner5.setSelectionId(55L);
        runner4.setRunnerName("999 Runner");
        runner5.setRunnerName("55 Runner");
        List<Runner> runners2 = Arrays.asList(runner3, runner4, runner5);

        MarketCatalogue marketCatalogue = MarketCatalogue.builder()
                .marketId("1.1")
                .marketName("New Market")
                .runners(runners2)
                .eventName("event")
                .eventTypeName("eventType")
                .competitionName("competition")
                .build();

        Enricher enricher = new Enricher();
        enricher.enrichMessage(message, List.of(marketCatalogue));

        assertEquals("New Market", message.getMc().get(0).getMarketName());
        assertEquals("999 Runner", message.getMc().get(0).getRc().get(0).getRunnerName());
        assertEquals(999L, message.getMc().get(0).getRc().get(0).getSelectionId());
        assertEquals("55 Runner", message.getMc().get(0).getRc().get(1).getRunnerName());
        assertEquals(55L, message.getMc().get(0).getRc().get(1).getSelectionId());
        assertEquals("event", message.getMc().get(0).getEventName());
        assertEquals("eventType", message.getMc().get(0).getEventTypeName());
        assertEquals("competition", message.getMc().get(0).getCompetitionName());
    }
}
