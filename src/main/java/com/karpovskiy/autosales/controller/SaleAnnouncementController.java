package com.karpovskiy.autosales.controller;

import com.karpovskiy.autosales.model.SaleAnnouncement;
import com.karpovskiy.autosales.service.implementation.SaleAnnouncementServiceImpl;
import com.karpovskiy.autosales.service.implementation.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class SaleAnnouncementController {

    private final SaleAnnouncementServiceImpl saleAnnouncementService;
    private final UserServiceImpl userService;

    @GetMapping
    public String getMainPage(Model model, @RequestParam(name = "page", defaultValue = "1") int pageNumber){
        model.addAttribute("announcements", saleAnnouncementService.getSaleAnnouncements(pageNumber-1));
        model.addAttribute("numOfPages", saleAnnouncementService.getNumberOfPages());
        model.addAttribute("pageNumber", pageNumber);

        return "main";
    }

    @GetMapping("/announcements/{id}")
    public String getOneSaleAnnounce(Model model, @PathVariable("id") String id){
        model.addAttribute("announcement", saleAnnouncementService.getSaleAnnouncementById(id));

        return "oneSaleAnnounce";
    }

    @GetMapping("/announcements/newAnnouncement")
    public String getCreatingPage(@ModelAttribute("announcement") SaleAnnouncement saleAnnouncement){
        return "newAnnouncement";
    }

    @PostMapping("/announcements/newAnnouncement")
    public String createNewSaleAnnounce(@ModelAttribute("announcement") SaleAnnouncement saleAnnouncement){
        saleAnnouncementService.createSaleAnnouncement(saleAnnouncement, userService.getUserByUsername("nik"));

        return "redirect:/";
    }

    @GetMapping("/announcements/{id}/edit")
    public String getEditingPage(@PathVariable("id") String id, Model model){
        model.addAttribute("announcement", saleAnnouncementService.getSaleAnnouncementById(id));
        return "editAnnouncement";
    }

    @PatchMapping("/announcements/{id}")
    public String editSaleAnnouncement(@PathVariable("id") String id,
                                       @ModelAttribute("announcement") SaleAnnouncement saleAnnouncement){
        saleAnnouncementService.patchSaleAnnouncement(saleAnnouncement, id);

        return "redirect:/";
    }

    @DeleteMapping("/announcements/{id}")
    public String deleteSaleAnnouncement(@PathVariable("id") String id){
        saleAnnouncementService.deleteSaleAnnouncement(id);

        return "redirect:/";
    }
}
