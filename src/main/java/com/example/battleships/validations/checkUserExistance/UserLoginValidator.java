package com.example.battleships.validations.checkUserExistance;


import com.example.battleships.domain.models.UserModel;
import com.example.battleships.domain.models.binding.UserLoginModel;
import com.example.battleships.domain.models.binding.UserRegisterModel;
import com.example.battleships.services.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UserLoginValidator implements ConstraintValidator<CheckUserExistence, UserLoginModel> {

    private final UserService userService;

    @Autowired
    public UserLoginValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void initialize(CheckUserExistence constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UserLoginModel userLoginModel, ConstraintValidatorContext constraintValidatorContext) {
        UserModel userModel = this.userService.findByUsername((userLoginModel.getUsername()));
        return  userModel.getId() != null
                && userModel.getPassword().equals(userLoginModel.getPassword());
    }
}
