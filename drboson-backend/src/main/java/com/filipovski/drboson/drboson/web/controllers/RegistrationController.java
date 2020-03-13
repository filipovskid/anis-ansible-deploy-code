package com.filipovski.drboson.drboson.web.controllers;

import com.filipovski.drboson.drboson.service.IUserService;
import com.filipovski.drboson.drboson.service.dtos.UserRegistrationDto;
import org.springframework.stereotype.Controller;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/auth", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
public class RegistrationController {

    private IUserService userService;

    public RegistrationController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/join")
    public void registerUser(@RequestBody @Valid UserRegistrationDto user) {
        userService.registerNewUserAccount(user);
    }

}
