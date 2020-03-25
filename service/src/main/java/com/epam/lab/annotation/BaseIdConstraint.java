package com.epam.lab.annotation;

import com.epam.lab.annotation.validator.BaseIdConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy= BaseIdConstraintValidator.class)
public @interface BaseIdConstraint {
    String message() default "Must be null or positive!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
