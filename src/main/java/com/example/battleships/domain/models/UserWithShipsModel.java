package com.example.battleships.domain.models;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserWithShipsModel {

    UserModel userModel;
    private List<ShipModel> shipModels;



}
