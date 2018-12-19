package ua.nure.nlebed.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ua.nure.nlebed.dto.UserDTO;
import ua.nure.nlebed.model.Role;
import ua.nure.nlebed.model.SupportedRoles;
import ua.nure.nlebed.model.User;
import ua.nure.nlebed.model.UserDetails;

import java.util.HashSet;
import java.util.Set;

@Component
public class UserDTOConverter {

    private static final Boolean NOT_BLOCKED = false;
    private static final String DEFAULT_PASSWORD = "password";

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User convert(UserDTO userDTO) {
        Set<Role> roles = new HashSet<>();
        roles.add(new Role(SupportedRoles.ROLE_CLIENT));

        Set<UserDetails> userDetails = new HashSet<>();
        userDetails.add(new UserDetails(
                userDTO.getMacAddress(),
                userDTO.getIpAddress(),
                userDTO.getDevice(),
                NOT_BLOCKED));

        return new User(userDTO.getEmail(),
                passwordEncoder.encode(DEFAULT_PASSWORD),
                userDTO.getName(),
                userDTO.getLastName(),
                roles,
                userDetails);
    }

}
