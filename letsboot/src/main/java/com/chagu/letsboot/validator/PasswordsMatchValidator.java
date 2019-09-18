package com.chagu.letsboot.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.chagu.letsboot.model.Users;

/**
 * @author Pratap Bhanu
 *
 */
public class PasswordsMatchValidator implements ConstraintValidator<PasswordsMatch, Users> {

    @Override
    public void initialize(PasswordsMatch passwordsMatch){
    }

    public boolean isValid(Users user, ConstraintValidatorContext context) {
        return user.getPassword().equals(user.getConfirmPassword());
    }

}