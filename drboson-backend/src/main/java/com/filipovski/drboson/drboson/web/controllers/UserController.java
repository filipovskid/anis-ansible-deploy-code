package com.filipovski.drboson.drboson.web.controllers;

import com.filipovski.drboson.drboson.model.User;
import com.filipovski.drboson.drboson.repository.UserRepository;
import com.filipovski.drboson.drboson.service.UserService;
import com.filipovski.drboson.drboson.service.dtos.UserProjection;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<UserProjection> me(@AuthenticationPrincipal User user) {
        UserProjection userProjection = userService.getUserDetails(user.getUsername());

        return ResponseEntity.ok(userProjection);
    }
}
