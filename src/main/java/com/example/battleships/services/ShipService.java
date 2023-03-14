package com.example.battleships.services;

import com.example.battleships.domain.entities.Ship;
import com.example.battleships.domain.enums.CategoryType;
import com.example.battleships.domain.helpers.LoggedUser;
import com.example.battleships.domain.models.CategoryModel;
import com.example.battleships.domain.models.ShipModel;
import com.example.battleships.domain.models.UserModel;
import com.example.battleships.domain.models.binding.AddShipModel;
import com.example.battleships.domain.models.binding.BattlingShipsModel;
import com.example.battleships.repos.ShipRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipService {

    private final ShipRepository shipRepository;
    private final ModelMapper modelMapper;
    private final LoggedUser loggedUser;
    private final UserService userService;
    private final CategoryService categoryService;

    @Autowired
    public ShipService(ShipRepository shipRepository, ModelMapper modelMapper, LoggedUser loggedUser, UserService userService, CategoryService categoryService) {
        this.shipRepository = shipRepository;
        this.modelMapper = modelMapper;
        this.loggedUser = loggedUser;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    public void addShip(AddShipModel shipModel){
        UserModel user = this.userService.findById(this.loggedUser.getId());
        CategoryModel category = this.categoryService.findByName(CategoryType.valueOf(shipModel.getCategory()));

        Ship shipToSave = this.modelMapper.map(new ShipModel().builder()
                .category(category)
                .created(shipModel.getCreated())
                .name(shipModel.getName())
                .health(shipModel.getHealth())
                .power(shipModel.getPower())
                .user(user)
                .build(), Ship.class);

        this.shipRepository.saveAndFlush(shipToSave);
    }

    public List<ShipModel> findAllByUserId(Long id){
        return this.shipRepository.findAllByUserId(id)
                .orElseThrow()
                .stream()
                .map(ship -> this.modelMapper.map(ship, ShipModel.class))
                .toList();
    }

    public void fight(BattlingShipsModel battlingShipsModel) {
        Ship loggedUserShip = this.shipRepository.findById(battlingShipsModel.getLoggedUserShipID()).orElseThrow();
        Ship notLoggedUserShip = this.shipRepository.findById(battlingShipsModel.getNotLoggedUserShipID()).orElseThrow();

        notLoggedUserShip.setHealth(notLoggedUserShip.getHealth() -loggedUserShip.getPower());

        if(notLoggedUserShip.getHealth() <= 0){
            this.shipRepository.deleteById(notLoggedUserShip.getId());
        }else{
            this.shipRepository.saveAndFlush(notLoggedUserShip);
        }
    }
}
