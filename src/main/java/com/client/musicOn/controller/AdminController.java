package com.client.musicOn.controller;

import com.client.musicOn.entity.Users;
import com.client.musicOn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;


        @GetMapping
        public String showAdminPage(Model theModel){

            List<Users> users = userService.findAllUsers();

            theModel.addAttribute("users",users);

            return "admin";
        }

        @RequestMapping("/deleteUser/{username}")
        public String deleteUser(@PathVariable(name="username") String username, Principal principal, Model theModel) {
            System.out.println(principal.getName());
            System.out.println(username);
            if(principal.getName().equals(username)){
                theModel.addAttribute("deleteMessage","You can't delete your own account");
                List<Users> users = userService.findAllUsers();

                theModel.addAttribute("users",users);
                return "admin";
            }else{
                userService.deleteUser(username);
            }
            return "redirect:/admin";
        }

        @GetMapping("/grantAdminAccess/{username}")
    public String grantAdminAccess(@PathVariable(name="username") String username){

        userService.grantAdminAccess(username);

            return "redirect:/admin";
        }

        @GetMapping("/takeAdminAccess/${username}")
    public String takeAdminAccess(@PathVariable(name="username") String username,Principal principal){


            return "redirect:/admin";
        }

}
