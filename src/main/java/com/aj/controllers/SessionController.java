package com.aj.controllers;

import com.aj.api.ApiClient;
import com.aj.api.UrlBuilder;
import com.aj.models.UserSession;
import com.aj.repositories.UserSessionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@Controller
public class SessionController {

    private final UserSessionRepository userSessionRepository;
    private final ObjectMapper objectMapper;

    public SessionController(UserSessionRepository userSessionRepository, ObjectMapper objectMapper) {
        this.userSessionRepository = userSessionRepository;
        this.objectMapper = objectMapper;
    }

    @RequestMapping("/")
    public String getIndex() {
        return "index";
    }

    @PostMapping("/sessions/new")
    public String login(
            @RequestParam("username") String username,
            @RequestParam("password") String password)
            throws IOException {

        ApiClient apiClient = new ApiClient();
        UrlBuilder urlBuilder = new UrlBuilder();
        HttpUrl url = urlBuilder.createLoginUrl(username, password);
        String response = apiClient.loginCall(url);
        UserSession userSession = objectMapper.readValue(response, UserSession.class);
        userSessionRepository.save(userSession);
        return "redirect:/welcome";
    }

    @RequestMapping("/welcome")
    public String getWelcome(Model model) {
        List<UserSession> userSessions = ((List<UserSession>) userSessionRepository.findAll());
        if (userSessions.size() > 0) {
            UserSession userSession = userSessions.get(userSessions.size() - 1);
            model.addAttribute("userSessions", userSession);
            return "welcome";
        }
        return "redirect:/";
    }
}
