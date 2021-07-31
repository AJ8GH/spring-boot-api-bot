package com.aj.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OrderController {

    @RequestMapping("/listCurrentOrders")
    public String listCurrentOrders() {
        return "/listCurrentOrders";
    }
}
