package com.example.battleships.domain.models.binding;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BattlingShipsModel {
    private Long loggedUserShipID;
    private Long notLoggedUserShipID;
}
