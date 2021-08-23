package com.aj.controllers;

import com.aj.api.ApiClientService;
import com.aj.deserialisation.DeserialisationService;
import com.aj.domain.bettingtypes.UserSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class UserSessionController extends AbstractController {
    private final String REDIRECT = "redirect:";
    private final String LOGIN = "/login";
    private final String SESSIONS_NEW = "/sessions/new";
    private final String INDEX_ROUTE = "/";
    private final String INDEX_VIEW = "index";

    private final ApiClientService apiClient;
    private final DeserialisationService jsonDeserialiser;

    @RequestMapping(INDEX_ROUTE)
    public String getIndex() {
        if (isNotLoggedIn(apiClient.getUserSession())) return REDIRECT + LOGIN;
        return INDEX_VIEW;
    }

    @RequestMapping(LOGIN)
    public String login(Model model) {
        model.addAttribute("userSession", apiClient.getUserSession());
        return LOGIN;
    }

    @PostMapping(SESSIONS_NEW)
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password)
            throws Exception {

        String response = apiClient.login(username, password);
        UserSession userSession = jsonDeserialiser
                .mapToObject(response, UserSession.class);
        apiClient.setUserSession(userSession);
        return REDIRECT + INDEX_ROUTE;
    }
}
