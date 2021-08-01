package com.aj.deserialisation;

import com.aj.models.Bet;
import com.aj.models.Event;
import com.aj.models.EventType;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface DeserialisationService {
    public <T> T mapToObject(String json, Class<T> classType) throws JsonProcessingException;
    public List<EventType> mapToEventTypeList(String json) throws JsonProcessingException;
    public List<Event> mapToEventList(String json) throws JsonProcessingException;
    public List<Bet> mapToBetList(String json) throws JsonProcessingException;
}
