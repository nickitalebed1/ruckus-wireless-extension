package ua.nure.nlebed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ua.nure.nlebed.model.Role;
import ua.nure.nlebed.model.SupportedRoles;
import ua.nure.nlebed.model.User;
import ua.nure.nlebed.repository.UserRepository;
import ua.nure.nlebed.service.UserService;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class SpringBootWebApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootWebApplication.class, args);
    }

    @Override
    public void run(String... strings) {
//        User user = new User();
//        Set<Role> roles = new HashSet<>();
//        roles.add(new Role(SupportedRoles.ROLE_ADMIN));
//        user.setEmail("admin@nure.ua");
//        user.setLastName("admin");
//        user.setName("admin");
//        user.setPassword(passwordEncoder.encode("admin"));
//        user.setRoles(roles);
//        user.setPhotoUrl("https://www.timeshighereducation.com/sites/default/files/byline_photos/default-avatar.png");
//        userRepository.saveAndFlush(user);
    }
}