package com.aj.controllers;

import com.aj.domain.UserSession;
import org.springframework.stereotype.Controller;

import java.util.Objects;

@Controller
public abstract class AbstractController {
    protected boolean isNotLoggedIn(UserSession userSession) {
        return (Objects.isNull(userSession) ||
                !userSession.getStatus().equals("SUCCESS"));
    }
}
