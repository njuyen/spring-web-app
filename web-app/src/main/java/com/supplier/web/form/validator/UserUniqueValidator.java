package com.supplier.web.form.validator;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.supplier.domain.User;
import com.supplier.repository.UserRepository;

public class UserUniqueValidator implements ConstraintValidator<UserUnique, Object> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void initialize(UserUnique constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Optional<User> user = userRepository.findOneByName(String.valueOf(value)); 
        return !user.isPresent();
    }

}
