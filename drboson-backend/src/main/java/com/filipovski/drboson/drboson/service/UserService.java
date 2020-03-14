package com.filipovski.drboson.drboson.service;

import com.filipovski.drboson.drboson.model.User;
import com.filipovski.drboson.drboson.repository.UserRepository;
import com.filipovski.drboson.drboson.service.dtos.UserRegistrationDto;
import com.filipovski.drboson.drboson.service.exceptions.UsernameAlreadyExistsException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User registerNewUserAccount(UserRegistrationDto user) throws UsernameAlreadyExistsException {
        if(usernameExists(user.getUsername()))
            throw new UsernameAlreadyExistsException("The username already exists");

        User registeringUser = User.withUsername(user.getUsername().toLowerCase())
                .password(user.getPassword())
                .email(user.getEmail())
                .name(StringUtils.capitalize(user.getUsername().toLowerCase()))
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .passwordEncoder(passwordEncoder::encode)
                .build();

        return userRepository.save(registeringUser);
    }

    private boolean usernameExists(String username) {
        return userRepository.existsUserByUsername(username);
    }
}
