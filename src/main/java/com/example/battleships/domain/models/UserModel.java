package com.example.battleships.domain.models;

import com.example.battleships.domain.entities.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class UserModel{
    private Long id;
    private String username;
    private String fullName;
    private String password;
    private String email;

}
