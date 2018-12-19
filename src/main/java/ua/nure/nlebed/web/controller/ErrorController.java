package ua.nure.nlebed.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {

    @RequestMapping("/error-page")
    public String error() {
        return "error/4xx";
    }

}
