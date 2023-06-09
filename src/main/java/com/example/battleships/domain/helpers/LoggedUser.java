package com.example.battleships.domain.helpers;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoggedUser {

    private Long id;

    public Boolean isValid(){
        return this.id != null;
    }

    public void clearUser() {
        this.id = null;
    }

}
