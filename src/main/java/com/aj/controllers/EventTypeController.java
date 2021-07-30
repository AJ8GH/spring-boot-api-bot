package com.aj.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EventTypeController {

    @RequestMapping("/eventTypes/list")
    public String getEventTypes() {
        return "eventTypes/list";
    }
}
