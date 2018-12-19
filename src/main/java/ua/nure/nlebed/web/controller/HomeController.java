package ua.nure.nlebed.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ua.nure.nlebed.web.Navigation;
import ua.nure.nlebed.web.Section;

@Controller
@Navigation(Section.HOME)
public class HomeController {

    @GetMapping({"/home", "/", "/index"})
    public String home() {
        return "index";
    }
}
