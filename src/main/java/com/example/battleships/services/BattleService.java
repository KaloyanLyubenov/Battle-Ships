package com.example.battleships.services;

import com.example.battleships.domain.helpers.LoggedUser;
import com.example.battleships.domain.models.ShipModel;
import com.example.battleships.domain.models.UserModel;
import com.example.battleships.domain.models.UserWithShipsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BattleService {

    private final UserService userService;
    private final ShipService shipService;

    @Autowired
    public BattleService(UserService userService, ShipService shipService) {
        this.userService = userService;
        this.shipService = shipService;
    }

    public UserWithShipsModel getUserWithShips(Long id) {
        UserModel logged = this.userService.findById(id);
        List<ShipModel> loggedUserShips  = this.shipService.findAllByUserId(id);

        return UserWithShipsModel
                .builder()
                    .userModel(logged)
                    .shipModels(loggedUserShips)
                .build();
    }

}
