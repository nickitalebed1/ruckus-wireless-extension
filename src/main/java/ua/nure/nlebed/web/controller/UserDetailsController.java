package ua.nure.nlebed.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.nure.nlebed.model.User;
import ua.nure.nlebed.service.UserService;
import ua.nure.nlebed.web.Navigation;
import ua.nure.nlebed.web.Section;

import java.security.Principal;

@Controller
@Navigation(Section.USERDETAILS)
public class UserDetailsController {

    private static final String USERDETAILS_INDEX = "userdetails/index";

    @Autowired
    private UserService userService;

    @RequestMapping("/profile")
    public String userDetails(Model model, Principal principal) {
        User user = userService.findUserByEmail(principal.getName());
        model.addAttribute("user", user);
        return USERDETAILS_INDEX;
    }

    @RequestMapping("/userDetails/{id}")
    public String clientDetails(Model model, @PathVariable Integer id) {
        User user = userService.findUserById(id);
        model.addAttribute("user", user);
        return USERDETAILS_INDEX;
    }


}
