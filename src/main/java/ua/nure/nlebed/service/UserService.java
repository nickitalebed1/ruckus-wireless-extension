package ua.nure.nlebed.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nure.nlebed.converter.UserDTOConverter;
import ua.nure.nlebed.dto.UserDTO;
import ua.nure.nlebed.model.User;
import ua.nure.nlebed.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserDTOConverter userDTOConverter;

    @Autowired
    public UserService(UserRepository userRepository, UserDTOConverter userDTOConverter) {
        this.userRepository = userRepository;
        this.userDTOConverter = userDTOConverter;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void saveUser(UserDTO userDTO) {
        User user = userDTOConverter.convert(userDTO);
        userRepository.save(user);
    }

}
