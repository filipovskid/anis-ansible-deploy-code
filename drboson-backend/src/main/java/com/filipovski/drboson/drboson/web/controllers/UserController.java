package com.filipovski.drboson.drboson.web.controllers;

import com.filipovski.drboson.drboson.model.User;
import com.filipovski.drboson.drboson.repository.UserRepository;
import com.filipovski.drboson.drboson.service.dtos.UserProjection;
import org.apache.jena.tdb.lib.StringAbbrev;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/me")
    public ResponseEntity<UserProjection> me(Authentication authentication) {
        if(!authentication.isAuthenticated())
            return ResponseEntity.badRequest().build();

        User user = (User) authentication.getPrincipal();
        UserProjection userProjection = userRepository.getUserProjectionByUsername(user.getUsername());

        return ResponseEntity.ok(userProjection);
    }
}
