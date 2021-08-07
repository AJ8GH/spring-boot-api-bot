package com.aj.controllers;

import com.aj.api.ApiClient;
import com.aj.api.ApiClientService;
import com.aj.deserialisation.DeserialisationService;
import com.aj.deserialisation.JsonDeserialiser;
import com.aj.models.UserSession;
import com.aj.repositories.UserSessionRepository;
import lombok.AllArgsConstructor;
import org.springframework.core.serializer.DefaultSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class UserSessionController {
    private final UserSessionRepository userSessionRepository;
    private final ApiClientService apiClient;
    private final DeserialisationService jsonDeserialiser;

    @RequestMapping("/")
    public String getIndex() {
        if (apiClient.getUserSession() == null ||
                !apiClient.getUserSession().getStatus().equals("SUCCESS")) {
            return "redirect:/login";
        }
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
        userSessionRepository.save(userSession);
        apiClient.setUserSession(userSession);

        if (userSession.getStatus().equals("SUCCESS")) return "redirect:/";
        return "redirect:/login";
    }
}
