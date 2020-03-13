package com.filipovski.drboson.drboson.service.dtos;


import com.filipovski.drboson.drboson.service.validation.PasswordMatches;
import com.filipovski.drboson.drboson.service.validation.ValidEmail;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@Data
@PasswordMatches
public class UserRegistrationDto {
    @NotEmpty(message = "Username must not be empty")
    private String username;

    @ValidEmail
    @NotEmpty(message = "Email must not be empty")
    private String email;

    @NotEmpty(message = "Password must not be empty")
    private String password;
    private String passwordConfirmation;
}
