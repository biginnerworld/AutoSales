package com.karpovskiy.autosales.controller;

import com.karpovskiy.autosales.model.SaleAnnouncement;
import com.karpovskiy.autosales.model.User;
import com.karpovskiy.autosales.service.SaleAnnouncementService;
import com.karpovskiy.autosales.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class SaleAnnouncementController {

    private final SaleAnnouncementService saleAnnouncementService;

    @GetMapping
    public String getMainPage(Model model){
        model.addAttribute("announcements", saleAnnouncementService.getAllAnnouncements());
        return "main";
    }

}
