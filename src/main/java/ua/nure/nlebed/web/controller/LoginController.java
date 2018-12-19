package ua.nure.nlebed.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.nure.nlebed.web.Navigation;
import ua.nure.nlebed.web.Section;

@Controller
@Navigation(Section.LOGIN)
public class LoginController {

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
