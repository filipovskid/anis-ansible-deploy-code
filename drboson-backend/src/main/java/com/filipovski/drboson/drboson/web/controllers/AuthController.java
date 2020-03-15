package com.filipovski.drboson.drboson.web.controllers;

import com.filipovski.drboson.drboson.model.jwt.AuthenticationRequest;
import com.filipovski.drboson.drboson.model.jwt.AuthenticationResponse;
import com.filipovski.drboson.drboson.service.IUserService;
import com.filipovski.drboson.drboson.service.UserService;
import com.filipovski.drboson.drboson.service.dtos.UserRegistrationDto;
import com.filipovski.drboson.drboson.service.exceptions.UsernameAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RequestMapping(path = "/auth", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
public class AuthController {

    private final IUserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> loginUser(@RequestBody AuthenticationRequest authenticationRequest,
                                                            HttpServletResponse response) {
        AuthenticationResponse authenticationResponse = userService.userLogin(authenticationRequest, response);

        return ResponseEntity.ok(authenticationResponse);
    }

    @PostMapping("/join")
    @ResponseStatus(HttpStatus.OK)
    public void registerUser(@RequestBody @Valid UserRegistrationDto user) throws UsernameAlreadyExistsException {
        userService.registerNewUserAccount(user);
    }
}
