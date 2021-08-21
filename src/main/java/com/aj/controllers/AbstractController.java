package com.aj.controllers;

import com.aj.models.UserSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;

@Controller
public abstract class AbstractController {
    protected boolean isNotLoggedIn(UserSession userSession) {
        return (Objects.isNull(userSession) ||
                !userSession.getStatus().equals("SUCCESS"));
    }
}
