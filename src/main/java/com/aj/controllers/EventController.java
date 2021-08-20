package com.aj.controllers;

import com.aj.api.ApiClientService;
import com.aj.deserialisation.DeserialisationService;
import com.aj.models.Event;
import com.aj.models.EventType;
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
    private final ApiClientService apiClient;
    private final DeserialisationService jsonDeserialiser;

    @RequestMapping("/events/listTypes")
    public String listEventTypes(Model model) throws IOException {
        if (isNotLoggedIn(apiClient.getUserSession())) return "redirect:/login";

        String response = apiClient.listEventTypes();
        List<EventType> eventTypes = jsonDeserialiser.mapToEventTypeList(response);
        model.addAttribute("eventTypes", eventTypes);
        return "events/listTypes";
    }

    @RequestMapping("/events/list/{eventTypeId}")
    public String listEvents(@PathVariable("eventTypeId") long eventTypeId,
                             Model model) throws IOException {
        if (isNotLoggedIn(apiClient.getUserSession())) return "redirect:/login";

        String response = apiClient.listEvents(eventTypeId);
        List<Event> events = jsonDeserialiser.mapToEventList(response);
        model.addAttribute("events", events);
        return "events/list";
    }
}
