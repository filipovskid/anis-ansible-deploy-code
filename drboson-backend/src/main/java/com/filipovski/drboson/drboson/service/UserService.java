package com.filipovski.drboson.drboson.service;

import com.filipovski.drboson.drboson.model.User;
import com.filipovski.drboson.drboson.model.jwt.AuthenticationRequest;
import com.filipovski.drboson.drboson.model.jwt.AuthenticationResponse;
import com.filipovski.drboson.drboson.service.dtos.UserProjection;
import com.filipovski.drboson.drboson.service.dtos.UserRegistrationDto;
import com.filipovski.drboson.drboson.service.exceptions.UsernameAlreadyExistsException;

import javax.servlet.http.HttpServletResponse;

public interface UserService {

    User registerNewUserAccount(UserRegistrationDto user) throws UsernameAlreadyExistsException;

    AuthenticationResponse userLogin(AuthenticationRequest authenticationRequest, HttpServletResponse response);

    UserProjection getUserDetails(String username);
}
