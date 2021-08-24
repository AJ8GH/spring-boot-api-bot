package com.aj.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppErrorController
        extends AbstractController
        implements ErrorController {
    private final String ERROR = "/error";

    @RequestMapping(ERROR)
    public String error() {
        return ERROR;
    }
}
