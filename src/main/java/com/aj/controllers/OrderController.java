package com.aj.controllers;

import com.aj.api.ApiClientService;
import com.aj.models.Order;
import com.aj.repositories.OrderRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Controller
public class OrderController {

    private final ApiClientService apiClient;
    private final ObjectMapper objectMapper;
    private final OrderRepository orderRepository;

    public OrderController(ApiClientService apiClient,
                           ObjectMapper objectMapper,
                           OrderRepository orderRepository) {
        this.apiClient = apiClient;
        this.objectMapper = objectMapper;
        this.orderRepository = orderRepository;
    }

    @RequestMapping("/listCurrentOrders")
    public String listCurrentOrders(Model model) throws IOException {
        String response = apiClient.listCurrentOrders();
        List<Order> orders = Arrays.asList(objectMapper.readValue(response, Order[].class));
        orderRepository.saveAll(orders);
        model.addAttribute("orders", orders);
        return "/listCurrentOrders";
    }
}
