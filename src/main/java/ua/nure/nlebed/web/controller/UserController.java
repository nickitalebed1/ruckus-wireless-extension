package ua.nure.nlebed.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ua.nure.nlebed.service.UserService;
import ua.nure.nlebed.web.pojo.UserPojo;

import javax.validation.Valid;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/addUser")
    public String toAddUserPage(Model model) {
        model.addAttribute("userPojo", new UserPojo());
        return "admin/addUser/index";
    }

    @PostMapping("/addUser")
    public String addUser(@ModelAttribute @Valid UserPojo userPojo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/addUser/index";
        }
        userService.saveUserPojo(userPojo);
        return "admin/addUser/index";
    }

}
