package com.simple.shop.core.validation;

import org.hibernate.validator.constraints.Length;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LengthValidator implements ConstraintValidator<Length, String> {

    private int minLength;
    private int maxLength;

    @Override
    public void initialize(Length length) {
        minLength = length.min();
        maxLength = length.max();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        String str = value.trim();
        return str.length() >= minLength && str.length() <= maxLength;
    }
}
