package ua.nure.nlebed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;
import ua.nure.nlebed.model.Role;
import ua.nure.nlebed.model.SupportedRoles;
import ua.nure.nlebed.model.User;
import ua.nure.nlebed.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@SpringBootApplication
@EnableScheduling
public class SpringBootWebApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    static {
        ShitInit.disableSslVerification();
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootWebApplication.class, args);
    }

    @Override
    public void run(String... strings) {
//        test();
        User user = new User();
        Set<Role> roles = new HashSet<>();
        roles.add(new Role(SupportedRoles.ROLE_ADMIN));
        user.setEmail("admin@nure.ua");
        user.setLastName("admin");
        user.setName("admin");
        user.setPassword(passwordEncoder.encode("admin"));
        user.setRoles(roles);
        user.setPhotoUrl("https://www.timeshighereducation.com/sites/default/files/byline_photos/default-avatar.png");
        userRepository.saveAndFlush(user);
    }

//    private void test() {
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(APPLICATION_JSON);
//
//        HttpEntity<String> entity = new HttpEntity<>("{\"userName\":\"admin\",\"password\":\"523243Zz!\"}", headers);
//        ResponseEntity<String> exchange = restTemplate.exchange("https://109.86.224.31:8444/wsg/api/scg/session/login", HttpMethod.PUT, entity, String.class);
//
//        String cookieValue = exchange.getHeaders().get("Set-Cookie").get(0);
//
//        HttpHeaders headers1 = new HttpHeaders();
//        headers1.setContentType(APPLICATION_JSON);
//        headers1.add("Cookie", cookieValue);
//        System.out.println(restTemplate.exchange("https://109.86.224.31:8444/wsg/api/scg/planes/systemSummary",
//                HttpMethod.GET, new HttpEntity<>(headers1), String.class).getBody());
//        restTemplate.exchange("https://109.86.224.31:8444/wsg/api/scg/aps/defaultValues?_dc=1",
//                HttpMethod.GET, new HttpEntity<>(headers1), String.class);
//
//    }

}