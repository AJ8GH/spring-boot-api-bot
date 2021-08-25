package com.aj.controllers;

import com.aj.api.ApiClient;
import com.aj.deserialisation.JsonDeserialiser;
import com.aj.domain.bettingtypes.Event;
import com.aj.domain.bettingtypes.EventType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

@Controller
@AllArgsConstructor
public class EventController extends AbstractController {
    private final String EVENTS_LIST_TYPES = "events/listTypes";
    private final String EVENTS_LIST = "events/list";

    private final ApiClient apiClient;
    private final JsonDeserialiser jsonDeserialiser;

    @RequestMapping(INDEX_ROUTE + EVENTS_LIST_TYPES)
    public String listEventTypes(Model model) throws IOException {
        if (isNotLoggedIn(apiClient.getUserSession())) return "redirect:/login";

        String response = apiClient.listEventTypes();
        List<EventType> eventTypes = jsonDeserialiser.mapToEventTypeList(response);
        model.addAttribute("eventTypes", eventTypes);
        return EVENTS_LIST_TYPES;
    }

    @RequestMapping(INDEX_ROUTE + EVENTS_LIST + "/{eventTypeId}")
    public String listEvents(@PathVariable("eventTypeId") String eventTypeId,
                             Model model) throws IOException {
        if (isNotLoggedIn(apiClient.getUserSession())) return "redirect:/login";

        String response = apiClient.listEvents(eventTypeId);
        List<Event> events = jsonDeserialiser.mapToEventList(response);
        model.addAttribute("events", events);
        return EVENTS_LIST;
    }
}
