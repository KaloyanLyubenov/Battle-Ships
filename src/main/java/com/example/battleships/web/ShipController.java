package com.example.battleships.web;

import com.example.battleships.domain.models.binding.AddShipModel;
import com.example.battleships.repos.ShipRepository;
import com.example.battleships.services.ShipService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/ships")
public class ShipController extends BaseController {

    private final ShipService shipService;

    public ShipController(ShipService shipService) {
        this.shipService = shipService;
    }

    @GetMapping("/add")
    public ModelAndView getAddShip(@ModelAttribute(name = "addShipModel")AddShipModel addShipModel) {
        return super.view("ship-add");
    }

    @PostMapping("/add")
    public ModelAndView postAddShip(@Valid @ModelAttribute(name = "addShipModel")AddShipModel addShipModel,
                                    BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addShipModel", addShipModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addShipModel"
                            ,bindingResult);

            return super.redirect("add");
        }

        this.shipService.addShip(addShipModel);

        return super.redirect("/home");
    }

    @ModelAttribute(name = "addShipModel")
    public AddShipModel addShipModel(){
        return new AddShipModel();
    }

}
