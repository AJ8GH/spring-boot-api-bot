package com.aj.controllers;

import com.aj.api.ApiClientService;
import com.aj.deserialisation.DeserialisationService;
import com.aj.models.UserSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
@AllArgsConstructor
public class UserSessionController extends AbstractController {
    private final ApiClientService apiClient;
    private final DeserialisationService jsonDeserialiser;

    @RequestMapping("/")
    public String getIndex() {
        if (isNotLoggedIn(apiClient.getUserSession())) return "redirect:/login";
        return "index";
    }

    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("userSession", apiClient.getUserSession());
        return "login";
    }

    @PostMapping("/sessions/new")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password)
            throws Exception {
        String response = apiClient.login(username, password);
        UserSession userSession = jsonDeserialiser.mapToObject(response, UserSession.class);
        apiClient.setUserSession(userSession);
        return "redirect:/";
    }
}
