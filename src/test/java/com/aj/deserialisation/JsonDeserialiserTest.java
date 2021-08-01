package com.aj.deserialisation;

import com.aj.helpers.ListOrdersResponse;
import com.aj.models.Bet;
import com.aj.models.EventType;
import com.aj.models.UserSession;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonDeserialiserTest {
    JsonDeserialiser jsonDeserialiser = new JsonDeserialiser(new ObjectMapper());

    @Test
    void mapToSessionObject() throws JsonProcessingException {
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
        assertEquals("SUCCESS", userSession.getStatus());
    }

    @Test
    void mapToEventTypeList() throws JsonProcessingException {
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
    void mapToOrders() throws IOException {
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
}
