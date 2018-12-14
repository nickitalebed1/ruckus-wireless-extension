package ua.nure.nlebed.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ua.nure.nlebed.web.Navigation;
import ua.nure.nlebed.web.Section;

@Navigation(Section.ADMIN)
@Controller
public class HomeController {
    @GetMapping({"/home", "/"})
    public String home() {
        return "user/index";
    }
}
