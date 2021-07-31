package com.aj.controllers;

import com.aj.api.ApiClientService;
import com.aj.models.EventType;
import com.aj.repositories.EventTypeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Controller
public class EventTypeController {
    private final EventTypeRepository eventTypeRepository;
    private final ApiClientService apiClient;
    private final ObjectMapper objectMapper;

    public EventTypeController(
            EventTypeRepository eventTypeRepository,
            ApiClientService apiClient,
            ObjectMapper objectMapper) {
        this.apiClient = apiClient;
        this.objectMapper = objectMapper;
        this.eventTypeRepository = eventTypeRepository;
    }

    @RequestMapping("/listEventTypes")
    public String lEventTypes(Model model) throws IOException {
        String response = apiClient.listEventTypes();
        List<EventType> eventTypes = Arrays.asList(objectMapper.readValue(response, EventType[].class));
        eventTypeRepository.saveAll(eventTypes);
        model.addAttribute("eventTypes", eventTypes);
        return "listEventTypes";
    }
}
