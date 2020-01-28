package com.client.musicOn.controller;

import com.client.musicOn.dao.RoleRepository;
import com.client.musicOn.dao.UserRepository;
import com.client.musicOn.entity.Roles;
import com.client.musicOn.entity.Users;
import com.client.musicOn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller

public class LoginController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/login")
    public String showLogin() {

        return "login";

    }
}
