package com.example.battleships.validations.checkShipExistence;

import com.example.battleships.validations.checkUserExistance.UserLoginValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = ShipExistenceValidator.class)
public @interface ValidateShipExistence {

    String message() default "Invalid ship";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default{};
}
