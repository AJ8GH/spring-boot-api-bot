package com.aj.controllers;

import com.aj.api.ApiClient;
import com.aj.api.ApiClientService;
import com.aj.deserialisation.JsonDeserialiser;
import com.aj.models.UserSession;
import com.aj.repositories.UserSessionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserSessionController {

    private final UserSessionRepository userSessionRepository;
    private final ApiClientService apiClient;
    private final JsonDeserialiser jsonDeserialiser;

    public UserSessionController(
            ApiClientService apiClient,
            UserSessionRepository userSessionRepository,
            JsonDeserialiser jsonDeserialiser) {

        this.userSessionRepository = userSessionRepository;
        this.apiClient = apiClient;
        this.jsonDeserialiser = jsonDeserialiser;
    }

    @RequestMapping("/")
    public String getIndex() {
        if (userSessionRepository.count() <= 0) return "redirect:/login";
        return "index";
    }

    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("userSession", ApiClient.getUserSession());
        return "login";
    }

    @PostMapping("/sessions/new")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password)
            throws Exception {

        String response = apiClient.login(username, password);
        UserSession userSession = jsonDeserialiser.mapToObject(response, UserSession.class);
        userSessionRepository.save(userSession);
        ApiClient.setUserSession(userSession);

        if (userSession.getStatus().equals("SUCCESS")) return "redirect:/";
        return "redirect:/login";
    }
}
