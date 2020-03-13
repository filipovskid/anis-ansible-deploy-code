package com.filipovski.drboson.drboson.service.validation;

import com.filipovski.drboson.drboson.service.dtos.UserRegistrationDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        UserRegistrationDto user = (UserRegistrationDto) o;
        return user.getPassword().equals(user.getPasswordConfirmation());
    }
}
