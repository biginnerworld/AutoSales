package com.karpovskiy.autosales.controller;

import com.karpovskiy.autosales.model.User;
import com.karpovskiy.autosales.service.implementation.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

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
    public String registerNewUser(@ModelAttribute("user") @Valid User user,
                                  BindingResult bindingResult,
                                  Model model){

        if (bindingResult.hasErrors()){
            return "registration";
        }
        if (userService.getUserByUsername(user.getUsername()) != null){
            model.addAttribute("message", "This username is already taken");
            return "registration";
        }

        userService.createUser(user);
        return "redirect:/users/login";
    }

}
