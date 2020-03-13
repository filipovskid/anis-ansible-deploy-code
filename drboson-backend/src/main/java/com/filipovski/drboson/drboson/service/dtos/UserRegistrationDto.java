package com.filipovski.drboson.drboson.service.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@Data
public class UserRegistrationDto {
    @NotEmpty
    private String username;

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;
    private String passwordConfirmation;
}
