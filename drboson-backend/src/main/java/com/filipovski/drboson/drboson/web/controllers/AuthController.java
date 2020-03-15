package com.filipovski.drboson.drboson.web.controllers;

import com.filipovski.drboson.drboson.model.User;
import com.filipovski.drboson.drboson.model.jwt.AuthenticationRequest;
import com.filipovski.drboson.drboson.model.jwt.AuthenticationResponse;
import com.filipovski.drboson.drboson.repository.UserRepository;
import com.filipovski.drboson.drboson.service.UserDetailsServiceImpl;
import com.filipovski.drboson.drboson.service.dtos.UserProjection;
import com.filipovski.drboson.drboson.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

@RestController
@CrossOrigin (origins = "http://localhost:3000", allowCredentials = "true")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public AuthController(AuthenticationManager authenticationManager,
                          UserDetailsServiceImpl userDetailsService,
                          JwtUtil jwtUtil, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    @PostMapping("/auth/login")
    public ResponseEntity<AuthenticationResponse> loginUser(@RequestBody AuthenticationRequest authenticationRequest,
                                                            HttpServletResponse response) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()));

        User user = (User) userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        String jwt = jwtUtil.generateToken(user);

        Cookie cookie = new Cookie(jwtUtil.getCookieName(), jwt);
        cookie.setPath("/");
//        cookie.setHttpOnly(true);
        response.addCookie(cookie);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @GetMapping("user/me")
    public ResponseEntity<UserProjection> me(Authentication authentication) {
        if(!authentication.isAuthenticated())
            return ResponseEntity.badRequest().build();

        User user = (User) authentication.getPrincipal();
        UserProjection userProjection = userRepository.getUserProjectionByUsername(user.getUsername());

        return ResponseEntity.ok(userProjection);
    }
}
