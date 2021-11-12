package com.karpovskiy.autosales.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class SaleAnnouncementController {

    @GetMapping
    public String getMainPage(){
        return "main";
    }
}
