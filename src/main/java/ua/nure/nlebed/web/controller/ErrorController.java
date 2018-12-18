package ua.nure.nlebed.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Controller
public class ErrorController {

    @ExceptionHandler(Throwable.class)
    public String error(Model model, Exception exception) {
        model.addAttribute("error", exception.getMessage());
        return "error/4xx";
    }


}
