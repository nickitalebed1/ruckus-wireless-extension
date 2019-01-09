package ua.nure.nlebed.web.rest;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Encoder;
import ua.nure.nlebed.dto.UserDTO;
import ua.nure.nlebed.model.User;
import ua.nure.nlebed.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class UserRestController {

    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public void putUser(@RequestBody UserDTO user) {
        userService.saveUser(user);
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public List<User> putUser() {
        return userService.findAllClients();
    }

    @RequestMapping(value = "/user/checkExist/{email}", method = RequestMethod.GET)
    public boolean checkExist(@PathVariable String email) {
        email = email + ".com";
        return userService.findUserByEmail(email) != null;
    }

}
