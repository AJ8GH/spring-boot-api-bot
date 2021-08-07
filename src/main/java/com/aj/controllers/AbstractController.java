package com.aj.controllers;

import com.aj.models.UserSession;
import org.springframework.stereotype.Controller;

@Controller
public abstract class AbstractController {
    protected boolean isLoggedIn(UserSession userSession) {
        return userSession.getStatus().equals("SUCCESS");
    }
}
