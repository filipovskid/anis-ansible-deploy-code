package com.filipovski.drboson.drboson.service;

import com.filipovski.drboson.drboson.model.User;
import com.filipovski.drboson.drboson.service.dtos.UserRegistrationDto;

public interface IUserService {

    User registerNewUserAccount(UserRegistrationDto user);
}
