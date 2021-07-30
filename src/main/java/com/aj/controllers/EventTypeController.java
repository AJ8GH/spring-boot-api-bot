package com.aj.controllers;

import com.aj.api.ApiClientService;
import com.aj.api.UrlBuilder;
import com.aj.models.EventType;
import com.aj.repositories.EventTypeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
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
    private final UrlBuilder urlBuilder;

    public EventTypeController(
            EventTypeRepository eventTypeRepository,
            ApiClientService apiClient,
            ObjectMapper objectMapper,
            UrlBuilder urlBuilder) {
        this.apiClient = apiClient;
        this.objectMapper = objectMapper;
        this.urlBuilder = urlBuilder;
        this.eventTypeRepository = eventTypeRepository;
    }

    @RequestMapping("/listEventTypes")
    public String getEventTypes(Model model) throws IOException {
        HttpUrl url = urlBuilder.createBettingUrl(urlBuilder.LIST_EVENT_TYPES);
        String response = apiClient.bettingCall(url, "");
        System.out.println(response);
        List<EventType> eventTypes = Arrays.asList(objectMapper.readValue(response, EventType[].class));
        eventTypeRepository.saveAll(eventTypes);
        model.addAttribute("eventTypes", eventTypeRepository);
        return "listEventTypes";
    }
}
