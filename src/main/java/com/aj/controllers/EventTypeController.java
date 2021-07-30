package com.aj.controllers;

import com.aj.api.ApiClientService;
import com.aj.api.UrlBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EventTypeController {

    private final ApiClientService apiClient;
    private final ObjectMapper objectMapper;
    private final UrlBuilder urlBuilder;

    public EventTypeController(
            ApiClientService apiClient,
            ObjectMapper objectMapper,
            UrlBuilder urlBuilder) {

        this.apiClient = apiClient;
        this.objectMapper = objectMapper;
        this.urlBuilder = urlBuilder;
    }

    @RequestMapping("/eventTypes/list")
    public String getEventTypes() {
        return "eventTypes/list";
    }
}
