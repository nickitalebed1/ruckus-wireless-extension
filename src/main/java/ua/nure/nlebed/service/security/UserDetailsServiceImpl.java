package ua.nure.nlebed.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.nure.nlebed.model.Role;
import ua.nure.nlebed.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        ua.nure.nlebed.model.User userByEmail = userService.findUserByEmail(email);
        if (userByEmail == null) {
            throw new UsernameNotFoundException("User " + email + " was not found in the database");
        }
        Set<Role> roles = userByEmail.getRoles();
        List<GrantedAuthority> grantList = new ArrayList<>();
        if (roles != null) {
            for (Role role : roles) {
                GrantedAuthority authority = new SimpleGrantedAuthority(role.getRole().name());
                grantList.add(authority);
            }
        }
        return new User(userByEmail.getEmail(), userByEmail.getPassword(), grantList);
    }

}
