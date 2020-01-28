package com.client.musicOn.controller;

import com.client.musicOn.entity.Users;
import com.client.musicOn.model.UnregisteredUser;
import com.client.musicOn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private UserService userService;



    @GetMapping("/registerForm")
    public String showRegister(Model theModel){
        UnregisteredUser theUser = new UnregisteredUser();
        theModel.addAttribute("newUser",theUser);
        return "register";
    }

    @PostMapping("/registerUser")
    public String registerUser(@Valid @ModelAttribute("newUser") UnregisteredUser user, BindingResult result, Model theModel){


        if(result.hasErrors()){
            return "register";
        }
        Users dbUser = userService.findUser(user.getUserName());

            if(dbUser.getUsername()!=null){
            UnregisteredUser theUser = new UnregisteredUser();
            theModel.addAttribute("userMsg","User already exists");
            theModel.addAttribute("newUser",theUser);
            return "register";
        }
        userService.saveUser(user);
        return "confirm-register";
    }



}
