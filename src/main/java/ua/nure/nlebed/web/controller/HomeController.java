package ua.nure.nlebed.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({"/home", "/", "/index"})
    public String home() {
        return "user/index";
    }

    @GetMapping("/login")
    public String login() {
        return "login/index";
    }

    @GetMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login/index";
    }
}
