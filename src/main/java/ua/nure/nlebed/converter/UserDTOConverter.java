package ua.nure.nlebed.converter;

import org.springframework.stereotype.Component;
import ua.nure.nlebed.dto.UserDTO;
import ua.nure.nlebed.model.Role;
import ua.nure.nlebed.model.User;
import ua.nure.nlebed.model.UserDetails;

import java.util.HashSet;
import java.util.Set;

@Component
public class UserDTOConverter {

    public User convert(UserDTO userDTO) {
        Set<Role> roles = new HashSet<>();
        roles.add(new Role("CLIENT"));

        Set<UserDetails> userDetails = new HashSet<>();
        userDetails.add(new UserDetails(
                userDTO.getMacAddress(),
                userDTO.getIpAddress(),
                userDTO.getDevice(),
                false));

        return new User(userDTO.getEmail(),
                "password",
                userDTO.getName(),
                userDTO.getLastName(),
                roles,
                userDetails);
    }

}
