package ua.nure.nlebed.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nure.nlebed.converter.UserDTOConverter;
import ua.nure.nlebed.dto.UserDTO;
import ua.nure.nlebed.model.SupportedRoles;
import ua.nure.nlebed.model.User;
import ua.nure.nlebed.model.UserDetails;
import ua.nure.nlebed.repository.UserRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final UserDTOConverter userDTOConverter;

    @Autowired
    public UserService(UserRepository userRepository, UserDTOConverter userDTOConverter) {
        this.userRepository = userRepository;
        this.userDTOConverter = userDTOConverter;
    }

    public List<User> findAllClients() {
        return userRepository.findAll().stream()
                .filter(u -> u.getRoles().stream().anyMatch(r -> r.getRole().equals(SupportedRoles.ROLE_CLIENT)))
                .collect(Collectors.toList());
    }

    public void saveUser(UserDTO userDTO) {
        User user = userDTOConverter.convert(userDTO);
        User userByEmail = userRepository.findByEmail(userDTO.getEmail());
        if (userByEmail == null) {
            LOGGER.info("User with email {} signed into the application.", userDTO.getEmail());
            userRepository.saveAndFlush(user);
        } else {
            userByEmail.setPhotoUrl(user.getPhotoUrl());
            Set<UserDetails> userDetails = userByEmail.getUserDetails();
            if (userDetails.stream().noneMatch(ud -> userDTO.getMacAddress().equals(ud.getMacAddress()))) {
                userDetails.add(new UserDetails(userDTO.getMacAddress(), userDTO.getIpAddress(),
                        userDTO.getDevice(), false));
            }
            userByEmail.setUserDetails(userDetails);
            userRepository.saveAndFlush(userByEmail);
        }
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findUserById(Integer id) {
        return userRepository.findOne(id);
    }

}
