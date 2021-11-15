package com.karpovskiy.autosales.controller;

import com.karpovskiy.autosales.service.SaleAnnouncementService;
import com.karpovskiy.autosales.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class SaleAnnouncementController {

    private final SaleAnnouncementService saleAnnouncementService;
    private final UserService userService;

    @GetMapping
    public String getMainPage(Model model, @RequestParam(name = "page", defaultValue = "1") int pageNumber){
        model.addAttribute("announcements", saleAnnouncementService.getSaleAnnouncements(pageNumber-1));
        model.addAttribute("numOfPages", saleAnnouncementService.getNumberOfPages());
        model.addAttribute("pageNumber", pageNumber);
        return "main";
    }
}
