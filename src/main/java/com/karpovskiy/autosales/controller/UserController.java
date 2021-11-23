package com.karpovskiy.autosales.controller;

import com.karpovskiy.autosales.model.User;
import com.karpovskiy.autosales.service.implementation.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Controller to handle users",
        description = "Controller implements CRUD methods for Users")
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    @GetMapping("/registration")
    @Operation(summary = "Returns registration page",
            description = "Allows to get registration page. Visible only for non authorized users")
    public String getRegistrationPage(@ModelAttribute("user") User user){
        return "registration";
    }


    @GetMapping("/login")
    @Operation(summary = "Returns login page",
            description = "Allows to get login page. Visible only for non authorized users")
    public String getLoginPage(){
        return "login";
    }

    @PostMapping("/registration")
    @Operation(summary = "Post to server new user to register",
            description = "Allows to register a new user.")
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
