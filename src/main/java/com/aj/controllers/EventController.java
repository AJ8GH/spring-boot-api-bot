package com.aj.controllers;

import com.aj.api.ApiClientService;
import com.aj.deserialisation.JsonDeserialiser;
import com.aj.models.Event;
import com.aj.models.EventType;
import com.aj.repositories.EventRepository;
import com.aj.repositories.EventTypeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

@Controller
public class EventController {
    private final EventTypeRepository eventTypeRepository;
    private final EventRepository eventRepository;
    private final ApiClientService apiClient;
    private final JsonDeserialiser jsonDeserialiser;

    public EventController(
            EventTypeRepository eventTypeRepository,
            EventRepository eventRepository,
            JsonDeserialiser jsonDeserialiser,
            ApiClientService apiClient) {

        this.eventTypeRepository = eventTypeRepository;
        this.eventRepository = eventRepository;
        this.jsonDeserialiser = jsonDeserialiser;
        this.apiClient = apiClient;
    }

    @RequestMapping("/listEventTypes")
    public String listEventTypes(Model model) throws IOException {
        String response = apiClient.listEventTypes();
        List<EventType> eventTypes = jsonDeserialiser.mapToEventTypeList(response);
        eventTypeRepository.saveAll(eventTypes);
        model.addAttribute("eventTypes", eventTypes);
        return "listEventTypes";
    }

    @RequestMapping("/listEvents{eventTypeId}")
    public String listEvents(@PathVariable("eventTypeId") long eventTypeId,
                             Model model) throws IOException {
        String response = apiClient.listEvents(eventTypeId);
        // List<Event> events = jsonDeserialiser.mapToEventList(response);
        // eventRepository.saveAll(events);
        // model.addAttribute("events", events);
        return "listEvents";
    }
}
