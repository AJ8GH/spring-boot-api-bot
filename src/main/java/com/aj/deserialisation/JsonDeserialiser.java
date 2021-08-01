package com.aj.deserialisation;

import com.aj.models.Bet;
import com.aj.models.EventType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class JsonDeserialiser implements DeserialisationService {

    private final ObjectMapper objectMapper;

    public JsonDeserialiser(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public <T> T mapToObject(String json, Class<T> classType)
            throws JsonProcessingException {
        return objectMapper.readValue(json, classType);
    }

    @Override
    public List<EventType> mapToEventTypeList(String json) throws JsonProcessingException {
        return Arrays.asList(objectMapper.readValue(json, EventType[].class));
    }

    @Override
    public List<Bet> mapToBetList(String json) throws JsonProcessingException {
        String content = String.join("", json.split("\\[|\\]")[1]);
        String jsonArray = "[" + content + "]";
        return Arrays.asList(objectMapper.readValue(jsonArray, Bet[].class));
    }
}