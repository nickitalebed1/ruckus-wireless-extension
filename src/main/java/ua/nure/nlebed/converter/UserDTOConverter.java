package ua.nure.nlebed.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ua.nure.nlebed.dto.UserDTO;
import ua.nure.nlebed.model.Role;
import ua.nure.nlebed.model.User;
import ua.nure.nlebed.model.UserDetails;
import ua.nure.nlebed.web.pojo.UserPojo;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static ua.nure.nlebed.model.SupportedRoles.ROLE_CLIENT;

@Component
public class UserDTOConverter {

    private static final Boolean NOT_BLOCKED = false;
    private static final String DEFAULT_PASSWORD = "password";

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User convert(UserDTO userDTO) {
        Set<Role> roles = new HashSet<>();
        roles.add(new Role(ROLE_CLIENT));

        Set<UserDetails> userDetails = new HashSet<>();
        userDetails.add(new UserDetails(
                userDTO.getMacAddress(),
                userDTO.getIpAddress(),
                userDTO.getDevice(),
                NOT_BLOCKED));

        String photoUrl = userDTO.getPhotoUrl() != null ? userDTO.getPhotoUrl() :
                "https://www.timeshighereducation.com/sites/default/files/byline_photos/default-avatar.png";

        return new User(userDTO.getEmail(),
                passwordEncoder.encode(DEFAULT_PASSWORD),
                userDTO.getName(),
                userDTO.getLastName(),
                photoUrl,
                roles,
                userDetails,
                LocalDateTime.now(),
                true);
    }

    public User convert(UserPojo userPojo) {
        Set<Role> roles = new HashSet<>();
        roles.add(new Role(ROLE_CLIENT));
        return new User(userPojo.getEmail(),
                passwordEncoder.encode(userPojo.getPassword()),
                userPojo.getName(),
                userPojo.getLastName(),
                "https://www.timeshighereducation.com/sites/default/files/byline_photos/default-avatar.png",
                roles,
                Collections.emptySet(),
                LocalDateTime.now(),
                false);
    }

}
