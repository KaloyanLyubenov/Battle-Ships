package com.example.battleships.web;

import com.example.battleships.domain.entities.Ship;
import com.example.battleships.domain.helpers.LoggedUser;
import com.example.battleships.domain.models.UserWithShipsModel;
import com.example.battleships.domain.models.binding.BattlingShipsModel;
import com.example.battleships.repos.ShipRepository;
import com.example.battleships.services.BattleService;
import com.example.battleships.services.ShipService;
import com.example.battleships.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController extends BaseController{

    private final LoggedUser loggedUser;
    private final BattleService battleService;
    private final UserService userService;
    private final ShipRepository shipRepository;
    private final ShipService shipService;

    @Autowired
    public HomeController(LoggedUser loggedUser, BattleService battleService, UserService userService, ShipRepository shipRepository, ShipService shipService) {
        this.loggedUser = loggedUser;
        this.battleService = battleService;
        this.userService = userService;
        this.shipRepository = shipRepository;
        this.shipService = shipService;
    }

    @GetMapping("home")
    public ModelAndView getHome(ModelAndView modelAndView){
        UserWithShipsModel logged = this.battleService.getUserWithShips(loggedUser.getId());
        UserWithShipsModel notLogged = this.battleService.getUserWithShips(this.userService.findByIdNot(loggedUser.getId()).getId());

        modelAndView.addObject("logged", logged);
        modelAndView.addObject("notLogged", notLogged);

        return super.view("home", modelAndView);
    }

    @PostMapping("battle")
    public ModelAndView postHome(@ModelAttribute(name = "battlingShipsModel") BattlingShipsModel battlingShipsModel){

        this.shipService.fight(battlingShipsModel);

        return super.redirect("home");
    }

    @GetMapping
    public ModelAndView getIndex(){
        return super.view("index");
    }

    @ModelAttribute(name = "battlingShipsModel")
    public BattlingShipsModel battlingShipsModel(){
        return new BattlingShipsModel();
    }

    @ModelAttribute(name = "allShips")
    public List<Ship> ships(){
        return this.shipRepository.findAll();
    }
}
