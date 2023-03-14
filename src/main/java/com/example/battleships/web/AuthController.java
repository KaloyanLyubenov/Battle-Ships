package com.example.battleships.web;

import com.example.battleships.domain.models.binding.UserLoginModel;
import com.example.battleships.domain.models.binding.UserRegisterModel;
import com.example.battleships.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/auth")
public class AuthController extends BaseController{

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/register")
    public ModelAndView getRegister(ModelAndView modelAndView, @ModelAttribute UserRegisterModel userRegisterModel) {
        modelAndView.addObject("userRegisterInfo", userRegisterModel);
        return super.view("register", modelAndView);
    }

    @PostMapping("/register")
    public ModelAndView postRegister(@Valid UserRegisterModel userRegisterModel,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.userRegisterModel", bindingResult)
                    .addFlashAttribute("userRegisterModel", userRegisterModel)
                    ;
            return super.view("register");
        }

        this.authService.registerUser(userRegisterModel);

        return super.view("login");
    }

    @GetMapping("/login")
    public ModelAndView getLogin(ModelAndView modelAndView, @ModelAttribute UserLoginModel userLoginModel) {
        modelAndView.addObject("userLoginModel", userLoginModel);
        return super.view("login");
    }

    @PostMapping("/login")
    public ModelAndView postLogin(@Valid @ModelAttribute(name = "userLoginModel") UserLoginModel userLoginModel,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userLoginModel", userLoginModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userLoginModel"
                        ,bindingResult);

            return super.redirect("login");
        }

        this.authService.loginUser(userLoginModel);

        return super.redirect("/home");
    }

    @GetMapping("/logout")
    public ModelAndView getLogout(){
        this.authService.logoutUser();
        return super.redirect("/");
    }

    @ModelAttribute(name = "userRegisterModel")
    public UserRegisterModel userRegisterModel(){
        return new UserRegisterModel();
    }

    @ModelAttribute(name = "userLoginModel")
    public UserLoginModel userLoginModel(){
        return new UserLoginModel();
    }


}
