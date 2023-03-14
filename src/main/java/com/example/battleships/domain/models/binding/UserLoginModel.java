package com.example.battleships.domain.models.binding;

import com.example.battleships.validations.checkUserExistance.CheckUserExistence;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@CheckUserExistence
public class UserLoginModel {

    @Size(min = 3, max = 10)
    @NotNull
    private String username;

    @Size(min = 3)
    @NotNull
    private String password;
}

