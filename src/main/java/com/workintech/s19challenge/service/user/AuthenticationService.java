package com.workintech.s19challenge.service.user;

import com.workintech.s19challenge.entity.user.Role;
import com.workintech.s19challenge.entity.user.User;
import com.workintech.s19challenge.repository.user.RoleRepository;
import com.workintech.s19challenge.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthenticationService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationService(UserRepository userRepository, RoleRepository roleRepository,
                                 PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(String name, String email, String password){
        String encodePassword = passwordEncoder.encode(password);
        Role userRole = roleRepository.findByAuthority("Customer").get();

        Set<Role> roles = new HashSet<>();
        roles.add(userRole);

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(encodePassword);
        user.setAuthorities(roles);

        return userRepository.save(user);
    }
}
