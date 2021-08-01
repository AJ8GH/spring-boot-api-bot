package com.aj.controllers;

import com.aj.api.ApiClientService;
import com.aj.deserialisation.JsonDeserialiser;
import com.aj.models.EventType;
import com.aj.repositories.EventTypeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.GeneratedValue;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Controller
public class EventTypeController {
    private final EventTypeRepository eventTypeRepository;
    private final ApiClientService apiClient;
    private final JsonDeserialiser jsonDeserialiser;

    public EventTypeController(
            EventTypeRepository eventTypeRepository,
            ApiClientService apiClient,
            JsonDeserialiser jsonDeserialiser) {

        this.apiClient = apiClient;
        this.jsonDeserialiser = jsonDeserialiser;
        this.eventTypeRepository = eventTypeRepository;
    }

    @RequestMapping("/listEventTypes")
    public String listEventTypes(Model model) throws IOException {
        String response = apiClient.listEventTypes();
        List<EventType> eventTypes = jsonDeserialiser.mapToEventTypeList(response);
        eventTypeRepository.saveAll(eventTypes);
        model.addAttribute("eventTypes", eventTypes);
        return "listEventTypes";
    }
}
