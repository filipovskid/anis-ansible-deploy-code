package com.filipovski.drboson.drboson.service;

import com.filipovski.drboson.drboson.model.User;
import com.filipovski.drboson.drboson.repository.UserRepository;
import com.filipovski.drboson.drboson.service.dtos.UserRegistrationDto;
import com.filipovski.drboson.drboson.service.exceptions.UsernameAlreadyExistsException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User registerNewUserAccount(UserRegistrationDto user) throws UsernameAlreadyExistsException {
        if(usernameExists(user.getUsername()))
            throw new UsernameAlreadyExistsException("The username already exists");

        User registeringUser = User.withUsername(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();

        return userRepository.save(registeringUser);
    }

    private boolean usernameExists(String username) {
        return userRepository.existsUserByUsername(username);
    }
}
