package com.karpovskiy.autosales.controller;

import com.karpovskiy.autosales.model.User;
import com.karpovskiy.autosales.service.implementation.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    @GetMapping("/registration")
    public String getRegistrationPage(@ModelAttribute("user") User user){
        return "registration";
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }

    @PostMapping("/registration")
    public String registerNewUser(@ModelAttribute("user") User user){
        userService.createUser(user);

        return "redirect:/";
    }
}
