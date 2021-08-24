package com.aj.controllers;

import com.aj.domain.bettingtypes.UserSession;
import org.springframework.stereotype.Controller;

import java.util.Objects;

@Controller
public abstract class AbstractController {
    protected final String INDEX_ROUTE = "/";
    protected final String INDEX_VIEW = "index";
    protected final String REDIRECT = "redirect:/";
    protected final String LOGIN = "login";

    protected boolean isNotLoggedIn(UserSession userSession) {
        return (Objects.isNull(userSession) ||
                !userSession.getStatus().equals("SUCCESS"));
    }
}
