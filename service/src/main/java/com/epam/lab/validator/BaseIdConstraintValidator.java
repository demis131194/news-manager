package com.epam.lab.validator;

import com.epam.lab.annotation.BaseIdConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BaseIdConstraintValidator implements ConstraintValidator<BaseIdConstraint, Long> {

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        return value == null || value > 0;
    }

}
