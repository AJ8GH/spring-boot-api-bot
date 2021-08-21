package com.aj.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppErrorController
        extends AbstractController
        implements ErrorController {

    @RequestMapping("/error")
    public String error() {
        return "/error";
    }
}
