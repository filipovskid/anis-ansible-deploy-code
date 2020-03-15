package com.filipovski.drboson.drboson.service;

import com.filipovski.drboson.drboson.model.User;
import com.filipovski.drboson.drboson.model.jwt.AuthenticationRequest;
import com.filipovski.drboson.drboson.model.jwt.AuthenticationResponse;
import com.filipovski.drboson.drboson.repository.UserRepository;
import com.filipovski.drboson.drboson.service.dtos.UserRegistrationDto;
import com.filipovski.drboson.drboson.service.exceptions.UsernameAlreadyExistsException;
import com.filipovski.drboson.drboson.util.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Service
public class UserService implements IUserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil,
                       AuthenticationManager authenticationManager, UserDetailsServiceImpl userDetailsService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
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

    @Override
    public AuthenticationResponse userLogin(AuthenticationRequest authenticationRequest, HttpServletResponse response) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()));

        User user = (User) userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        String jwt = jwtUtil.generateToken(user);

        Cookie cookie = new Cookie(jwtUtil.getCookieName(), jwt);
        cookie.setPath("/");
        response.addCookie(cookie);

        return new AuthenticationResponse(jwt);
    }

    private boolean usernameExists(String username) {
        return userRepository.existsUserByUsername(username);
    }
}
