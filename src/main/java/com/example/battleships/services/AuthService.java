package com.example.battleships.services;

import com.example.battleships.domain.entities.User;
import com.example.battleships.domain.helpers.LoggedUser;
import com.example.battleships.domain.models.binding.UserLoginModel;
import com.example.battleships.domain.models.binding.UserRegisterModel;
import com.example.battleships.repos.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final LoggedUser loggedUser;

    @Autowired
    public AuthService(UserRepository userRepository, ModelMapper modelMapper, LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.loggedUser = loggedUser;
    }

    public void registerUser(UserRegisterModel userRegisterModel){
        this.userRepository.saveAndFlush(this.modelMapper.map(userRegisterModel, User.class));
    }

    public void loginUser(UserLoginModel userLoginModel){
        User user = this.userRepository.findByUsername(userLoginModel.getUsername()).get();

        this.loggedUser.setId((user.getId()));
    }

    public void logoutUser(){
        loggedUser.clearUser();
    }


}
