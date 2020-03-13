package com.filipovski.drboson.drboson.web.controllers;

import com.filipovski.drboson.drboson.service.IUserService;
import com.filipovski.drboson.drboson.service.dtos.UserRegistrationDto;
import com.filipovski.drboson.drboson.service.exceptions.UsernameAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.ws.Response;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/auth", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
public class RegistrationController {

    private IUserService userService;

    public RegistrationController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/join")
    @ResponseStatus(HttpStatus.OK)
    public void registerUser(@RequestBody @Valid UserRegistrationDto user) throws UsernameAlreadyExistsException {
        userService.registerNewUserAccount(user);
    }

}
