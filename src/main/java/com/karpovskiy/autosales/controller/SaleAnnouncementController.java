package com.karpovskiy.autosales.controller;

import com.karpovskiy.autosales.model.SaleAnnouncement;
import com.karpovskiy.autosales.security.Role;
import com.karpovskiy.autosales.service.implementation.SaleAnnouncementServiceImpl;
import com.karpovskiy.autosales.service.implementation.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
    public String getMainPage(Model model,
                              @RequestParam(name = "page", defaultValue = "1") int pageNumber,
                              @RequestParam(name = "sort", defaultValue = "new") String sort,
                              @AuthenticationPrincipal UserDetails currentUser){
        model.addAttribute("announcements", saleAnnouncementService.getSaleAnnouncements(pageNumber-1, sort));
        model.addAttribute("numberOfAnnouncements", saleAnnouncementService.getAllAnnouncements().size());
        model.addAttribute("numOfPages", saleAnnouncementService.getNumberOfPages());
        model.addAttribute("pageNumber", pageNumber);
        if (currentUser != null){
            model.addAttribute("authorized", "true");
        }

        return "main";
    }

    @GetMapping("/announcements/{id}")
    public String getOneSaleAnnounce(Model model,
                                     @PathVariable("id") String id,
                                     @AuthenticationPrincipal UserDetails currentUser){
        SaleAnnouncement saleAnnouncement = saleAnnouncementService.getSaleAnnouncementById(id);
        model.addAttribute("announcement", saleAnnouncement);

        if (currentUser != null){
            model.addAttribute("authorized", "true");
            if (saleAnnouncement.getAuthor().getUsername().equals(currentUser.getUsername()) ||
                    userService.getUserByUsername(currentUser.getUsername()).getRole() == Role.ADMIN){
                model.addAttribute("changeable", "true");
            }
        }
        return "oneSaleAnnounce";
    }

    @GetMapping("/announcements/newAnnouncement")
    @PreAuthorize("hasAnyRole('ROLE_USER, ROLE_ADMIN')")
    public String getCreatingPage(@ModelAttribute("announcement") SaleAnnouncement saleAnnouncement){
        return "newAnnouncement";
    }

    @PostMapping("/announcements/newAnnouncement")
    @PreAuthorize("hasAnyRole('ROLE_USER, ROLE_ADMIN')")
    public String createNewSaleAnnounce(@AuthenticationPrincipal UserDetails author,
                                        @ModelAttribute("announcement") SaleAnnouncement saleAnnouncement){
        saleAnnouncementService.createSaleAnnouncement(saleAnnouncement, userService.getUserByUsername(author.getUsername()));

        return "redirect:/";
    }

    @GetMapping("/announcements/{id}/edit")
    @PreAuthorize("hasAnyRole('ROLE_USER, ROLE_ADMIN')")
    public String getEditingPage(@PathVariable("id") String id, Model model){
        model.addAttribute("announcement", saleAnnouncementService.getSaleAnnouncementById(id));
        return "editAnnouncement";
    }

    @PatchMapping("/announcements/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER, ROLE_ADMIN')")
    public String editSaleAnnouncement(@PathVariable("id") String id,
                                       @ModelAttribute("announcement") SaleAnnouncement saleAnnouncement){
        saleAnnouncementService.patchSaleAnnouncement(saleAnnouncement, id);

        return "redirect:/";
    }

    @DeleteMapping("/announcements/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER, ROLE_ADMIN')")
    public String deleteSaleAnnouncement(@PathVariable("id") String id){
        saleAnnouncementService.deleteSaleAnnouncement(id);

        return "redirect:/";
    }
}
