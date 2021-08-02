package com.aj.deserialisation;

import com.aj.helpers.ListMarketCatalogueResponse;
import com.aj.helpers.ListOrdersResponse;
import com.aj.models.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
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

        assertEquals(10883651431L, bets.get(0).getBetId());
        assertEquals("1.179033087", bets.get(0).getMarketId());
        assertEquals(13495803, bets.get(0).getSelectionId());
        assertEquals(3.0, bets.get(0).getPrice());
        assertEquals(0.1, bets.get(0).getSize());
        assertEquals("BACK", bets.get(0).getSide());
        assertEquals(0.0, bets.get(0).getBspLiability());
        assertEquals("EXECUTABLE", bets.get(0).getStatus());

        assertEquals(10883651820L, bets.get(1).getBetId());
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
        assertEquals("2021-08-15T13:00:00.000Z", events.get(0).getOpenDate());
        assertEquals(5, events.get(0).getMarketCount());

        assertEquals(29865701L, events.get(1).getId());
        assertEquals("Everton v Southampton", events.get(1).getName());
        assertEquals("GB", events.get(1).getCountryCode());
        assertEquals("GMT", events.get(1).getTimezone());
        assertEquals("2021-06-22T16:15:00.000Z", events.get(1).getOpenDate());
        assertEquals(6, events.get(1).getMarketCount());
    }

    @Test
    void testMapToMarketCatalogue() throws JsonProcessingException {
        String json = ListMarketCatalogueResponse.JSON;

        List<MarketCatalogue> marketCatalogueList = jsonDeserialiser.mapToMarketCatalogue(json);

        assertEquals("1.179345011", marketCatalogueList.get(0).getMarketId());
        assertEquals("Over/Under 0.5 Goals", marketCatalogueList.get(0).getMarketName());
        assertEquals(0.0, marketCatalogueList.get(0).getTotalMatched());

        assertEquals("1.179345012", marketCatalogueList.get(1).getMarketId());
        assertEquals("Over/Under 2.5 goals", marketCatalogueList.get(1).getMarketName());
        assertEquals(0.0, marketCatalogueList.get(1).getTotalMatched());

        List<Runner> runners = marketCatalogueList.get(0).getRunners();
        assertEquals(4699138, runners.get(0).getSelectionId());
        assertEquals("Under 0.5 Goals",runners.get(0).getRunnerName());
        assertEquals(1, runners.get(0).getSortPriority());

        runners = marketCatalogueList.get(1).getRunners();
        assertEquals(47973, runners.get(1).getSelectionId());
        assertEquals("Over 2.5 Goals",runners.get(1).getRunnerName());
        assertEquals(2, runners.get(1).getSortPriority());
    }
}
