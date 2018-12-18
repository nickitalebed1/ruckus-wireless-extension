package ua.nure.nlebed.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.nure.nlebed.service.UserService;
import ua.nure.nlebed.web.Navigation;
import ua.nure.nlebed.web.Section;

import java.security.Principal;

@Controller
@Navigation(Section.ADMIN)
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("users", userService.findAll());
        return "user/index";
    }

    @GetMapping("/userInfo")
    public String userInfo(Model model, Principal principal) {
        System.err.println(principal.getName());
        return "user/index";
    }
}
