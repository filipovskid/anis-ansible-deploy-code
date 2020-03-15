package com.filipovski.drboson.drboson.web.controllers;

import com.filipovski.drboson.drboson.model.jwt.AuthenticationRequest;
import com.filipovski.drboson.drboson.model.jwt.AuthenticationResponse;
import com.filipovski.drboson.drboson.service.IUserService;
import com.filipovski.drboson.drboson.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class AuthController {

    private final IUserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/auth/login")
    public ResponseEntity<AuthenticationResponse> loginUser(@RequestBody AuthenticationRequest authenticationRequest,
                                                            HttpServletResponse response) {
        AuthenticationResponse authenticationResponse = userService.userLogin(authenticationRequest, response);

        return ResponseEntity.ok(authenticationResponse);
    }
}
