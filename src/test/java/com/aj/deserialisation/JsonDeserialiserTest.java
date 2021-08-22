package com.aj.deserialisation;

import com.aj.domain.bettingtypes.*;
import com.aj.domain.esa.ResponseMessage;
import com.aj.helpers.ListMarketBookResponse;
import com.aj.helpers.ListMarketCatalogueResponse;
import com.aj.helpers.ListOrdersResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonDeserialiserTest {
    JsonDeserialiser jsonDeserialiser = new JsonDeserialiser(new ObjectMapper());

    @Test
    void testMapToSessionObject() throws JsonProcessingException {
        String json = "{\n" +
                "    \"token\": \"mockToken\",\n" +
                "    \"product\": \"testAccountApp\",\n" +
                "    \"status\": \"SUCCESS\",\n" +
                "    \"error\": \"\"\n" +
                "}";

        UserSession userSession = jsonDeserialiser.mapToObject(json, UserSession.class);

        assertEquals("mockToken", userSession.getToken());
        assertEquals("testAccountApp", userSession.getProduct());
        assertEquals("SUCCESS", userSession.getStatus());
        assertEquals("", userSession.getError());
    }

    @Test
    void testMapToEventTypeList() throws JsonProcessingException {
        String json = "[{\"eventType\":{\"id\":\"1\",\"name\":\"" +
                "Soccer\"},\"marketCount\":92},{\"eventType\":{\"id\"" +
                ":\"7522\",\"name\":\"Basketball\"},\"marketCount\":25}]";

        List<EventType> eventTypes = jsonDeserialiser.mapToEventTypeList(json);

        assertEquals(1, eventTypes.get(0).getId());
        assertEquals("Soccer", eventTypes.get(0).getName());
        assertEquals(92, eventTypes.get(0).getMarketCount());

        assertEquals(7522, eventTypes.get(1).getId());
        assertEquals("Basketball", eventTypes.get(1).getName());
        assertEquals(25, eventTypes.get(1).getMarketCount());
    }

    @Test
    void testMapToBetList() throws IOException {
        String json = ListOrdersResponse.JSON;

        List<Bet> bets = jsonDeserialiser.mapToBetList(json);

        assertEquals("10883651431", bets.get(0).getBetId());
        assertEquals("1.179033087", bets.get(0).getMarketId());
        assertEquals(13495803, bets.get(0).getSelectionId());
        assertEquals(3.0, bets.get(0).getPrice());
        assertEquals(0.1, bets.get(0).getSize());
        assertEquals("BACK", bets.get(0).getSide());
        assertEquals(0.0, bets.get(0).getBspLiability());
        assertEquals("EXECUTABLE", bets.get(0).getStatus());

        assertEquals("10883651820", bets.get(1).getBetId());
        assertEquals("1.179344408", bets.get(1).getMarketId());
        assertEquals(34, bets.get(1).getSelectionId());
        assertEquals(2.0, bets.get(1).getPrice());
        assertEquals(1.0, bets.get(1).getSize());
        assertEquals("LAY", bets.get(1).getSide());
        assertEquals(0.0, bets.get(1).getBspLiability());
        assertEquals("EXECUTABLE", bets.get(1).getStatus());
    }

    @Test
    void testMapToEventList() throws IOException {
        String json = "[{\"event\":{\"id\":\"29865702\",\"name\":\"" +
                "Newcastle v West Ham\",\"countryCode\":\"GB\",\"timezone\":\"" +
                "GMT\",\"openDate\":\"2021-08-15T13:00:00.000Z\"},\"marketCount" +
                "\":5},{\"event\":{\"id\":\"29865701\",\"name\":\"Everton v " +
                "Southampton\",\"countryCode\":\"GB\",\"timezone\":\"GMT\",\"" +
                "openDate\":\"2021-06-22T16:15:00.000Z\"},\"marketCount\":6}]";

        List<Event> events = jsonDeserialiser.mapToEventList(json);

        assertEquals(29865702L, events.get(0).getId());
        assertEquals("Newcastle v West Ham", events.get(0).getName());
        assertEquals("GB", events.get(0).getCountryCode());
        assertEquals("GMT", events.get(0).getTimezone());
        assertEquals(5, events.get(0).getMarketCount());

        String date = "2021-08-15T13:00:00.000Z";
        LocalDateTime parsedDate = LocalDateTime.parse(date.substring(0, date.length() - 1));
        assertEquals(parsedDate, events.get(0).parse(events.get(0).getOpenDate()));
        assertEquals(date, events.get(0).getOpenDate());

        assertEquals(29865701L, events.get(1).getId());
        assertEquals("Everton v Southampton", events.get(1).getName());
        assertEquals("GB", events.get(1).getCountryCode());
        assertEquals("GMT", events.get(1).getTimezone());
        assertEquals(6, events.get(1).getMarketCount());

        date = "2021-06-22T16:15:00.000Z";
        parsedDate = LocalDateTime.parse(date.substring(0, date.length() - 1));
        assertEquals(parsedDate, events.get(1).parse(events.get(1).getOpenDate()));
        assertEquals(date, events.get(1).getOpenDate());
    }

    @Test
    void testMapToMarketCatalogue() throws JsonProcessingException {
        String json = ListMarketCatalogueResponse.JSON;

        List<MarketCatalogue> marketCatalogueList = jsonDeserialiser.mapToMarketCatalogue(json);

        assertEquals("1.179345011", marketCatalogueList.get(0).getMarketId());
        assertEquals("Over/Under 0.5 Goals", marketCatalogueList.get(0).getMarketName());
        assertEquals(0.0, marketCatalogueList.get(0).getTotalMatched());
        assertEquals("Soccer", marketCatalogueList.get(0).getEventTypeName());
        assertEquals("Leicester v Wolves", marketCatalogueList.get(0).getEventName());
        assertEquals("English Premier League", marketCatalogueList.get(0).getCompetitionName());

        assertEquals("1.179345012", marketCatalogueList.get(1).getMarketId());
        assertEquals("Over/Under 2.5 goals", marketCatalogueList.get(1).getMarketName());
        assertEquals(0.0, marketCatalogueList.get(1).getTotalMatched());

        List<Runner> runners = marketCatalogueList.get(0).getRunners();
        assertEquals(4699138, runners.get(0).getSelectionId());
        assertEquals("Under 0.5 Goals",runners.get(0).getRunnerName());

        runners = marketCatalogueList.get(1).getRunners();
        assertEquals(47973, runners.get(1).getSelectionId());
        assertEquals("Over 2.5 Goals",runners.get(1).getRunnerName());
    }

    @Test
    void testMapToMarketBook() throws JsonProcessingException {
        String json = ListMarketBookResponse.JSON;

        MarketBook marketBook = jsonDeserialiser.mapToMarketBook(json);

        assertEquals("1.179465437", marketBook.getMarketId());
        assertEquals("OPEN", marketBook.getStatus().toString());
        assertEquals(true, marketBook.getComplete());
        assertEquals(19, marketBook.getNumberOfActiveRunners());
        assertEquals(0.0, marketBook.getTotalMatched());
        assertEquals(0.0, marketBook.getTotalAvailable());
    }

    @Test
    void testMapToMarketBookWithPrices() throws JsonProcessingException {
        String json = ListMarketBookResponse.JSON_WITH_PRICES;
        MarketBook marketBook = jsonDeserialiser.mapToMarketBook(json);
        List<Runner> runners = marketBook.getRunners();

        assertEquals(57405L, runners.get(0).getSelectionId());
        assertEquals(0.0, runners.get(0).getHandicap());
        assertEquals(0, runners.get(0).getAvailableToBack().size());
        assertEquals(1, runners.get(0).getAvailableToLay().size());
        assertEquals(1, runners.get(0).getTradedVolume().size());
        assertEquals(3.0, runners.get(0).getAvailableToLay().get(0).getPrice());
        assertEquals(6.0, runners.get(0).getAvailableToLay().get(0).getSize());

        assertEquals(57407L, runners.get(1).getSelectionId());
        assertEquals(0.0, runners.get(1).getHandicap());
        assertEquals(0, runners.get(1).getAvailableToBack().size());
        assertEquals(1, runners.get(1).getAvailableToLay().size());
        assertEquals(0, runners.get(1).getTradedVolume().size());
        assertEquals(3.0, runners.get(1).getAvailableToLay().get(0).getPrice());
        assertEquals(6.0, runners.get(1).getAvailableToLay().get(0).getSize());
    }

    @Test
    void testMapConfirmationResponseToBet() throws JsonProcessingException {
        String json = "{\"status\": \"SUCCESS\"," +
                "\"marketId\": \"1.179344550\"," +
                "\"instructionReports\": " +
                "[{\"status\": \"SUCCESS\"," +
                "\"betId\": \"10883830194\"}]}";

        Bet bet = jsonDeserialiser.mapToObject(json, Bet.class);

        assertEquals("SUCCESS", bet.getStatus());
        assertEquals("10883830194", bet.getBetId());
    }

    @Test
    void testMapToCancelExecutionReportObject() throws JsonProcessingException {
        String json = "{\"status\":\"SUCCESS\",\"marketId\":\"1.179344550\"," +
                "\"instructionReports\":[{\"status\":\"SUCCESS\",\"instruction\":" +
                "{\"betId\":\"10883830194\"},\"sizeCancelled\":0.1," +
                "\"cancelledDate\":\"2021-08-07T18:59:28.000Z\"}]}";

        var cancelExecutionReport = jsonDeserialiser.mapToObject(json, CancelExecutionReport.class);

        assertEquals("SUCCESS", cancelExecutionReport.getStatus());
        assertEquals("1.179344550", cancelExecutionReport.getMarketId());
        assertEquals(0.1, cancelExecutionReport.getInstructionReports().get(0).getSizeCancelled());
        assertEquals("2021-08-07T18:59:28.000Z", cancelExecutionReport.getInstructionReports().get(0).getCancelledDate());
    }

    @Test
    void testMapToResponseMessageObject() throws JsonProcessingException {
        String json = "{\"op\":\"mcm\",\"id\":0,\"mc\":[{\"id\":\"1.179268396\",\"rc\":[{\"batl\":[[0,2,1.7]],\n" +
                "\"bdatb\":[[0,0,0],[1,0,0],[2,0,0],[3,0,0],[4,0,0],[5,0,0],[6,0,0],[7,0,0],[8,0,0],[9,0,0]],\n" +
                "\"bdatl\":[[0,2,1.7],[1,0,0],[2,0,0],[3,0,0],[4,0,0],[5,0,0],[6,0,0],[7,0,0],[8,0,0],[9,0,0]],\n" +
                "\"id\":47999}],\"img\":true}]}";

        ResponseMessage responseMessage = jsonDeserialiser.mapToObject(json, ResponseMessage.class);

        assertEquals(0, responseMessage.getId());
        assertEquals("mcm", responseMessage.getOp());
        assertEquals(2, responseMessage.getMc().get(0).getRc().get(0).getBatl().get(0).get(1));
        assertEquals(1.7, responseMessage.getMc().get(0).getRc().get(0).getBatl().get(0).get(2));
    }
}
