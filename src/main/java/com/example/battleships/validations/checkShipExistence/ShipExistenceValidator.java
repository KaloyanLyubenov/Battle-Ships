package com.example.battleships.validations.checkShipExistence;

import com.example.battleships.repos.ShipRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ShipExistenceValidator implements ConstraintValidator<ValidateShipExistence, String> {

    private final ShipRepository shipRepository;

    public ShipExistenceValidator(ShipRepository shipRepository) {
        this.shipRepository = shipRepository;
    }

    @Override
    public void initialize(ValidateShipExistence constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String shipName, ConstraintValidatorContext constraintValidatorContext) {
        return this.shipRepository.findByName(shipName).isEmpty();
    }
}
