package com.karpovskiy.autosales.controller;

import com.karpovskiy.autosales.model.SaleAnnouncement;
import com.karpovskiy.autosales.security.Role;
import com.karpovskiy.autosales.service.implementation.SaleAnnouncementServiceImpl;
import com.karpovskiy.autosales.service.implementation.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@Tag(name = "Controller to handle Sale Announcements",
        description = "Controller implements CRUD methods for sale announcements")
@RequestMapping("/")
@AllArgsConstructor
public class SaleAnnouncementController {

    private final SaleAnnouncementServiceImpl saleAnnouncementService;
    private final UserServiceImpl userService;

    @GetMapping
    @Operation(summary = "Returns main page with announces",
            description = "Allows to get main page(announces divided by pagination) and allows you to sort announces")
    public String getMainPage(Model model,
                              @RequestParam(name = "page", defaultValue = "1") @Parameter(description = "Number of the page") int pageNumber,
                              @RequestParam(name = "sort", defaultValue = "new") @Parameter(description = "Sorting type") String sort,
                              @AuthenticationPrincipal @Parameter(description = "'on-line' user") UserDetails currentUser){
        model.addAttribute("announcements", saleAnnouncementService.getSaleAnnouncements(pageNumber-1, sort));
        model.addAttribute("numberOfAnnouncements", saleAnnouncementService.getAllAnnouncements().size());
        model.addAttribute("numOfPages", saleAnnouncementService.getNumberOfPages());
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("sort", sort);
        if (currentUser != null){
            model.addAttribute("authorized", "true");
        }

        return "main";
    }


    @GetMapping("/announcements/{id}")
    @Operation(summary = "Returns selected page",
            description = "Allow to get selected page and see the details of the announce")
    public String getOneSaleAnnounce(Model model,
                                     @PathVariable("id") @Parameter(description = "ID of the announcement") String id,
                                     @AuthenticationPrincipal @Parameter(description = "'on-line' user") UserDetails currentUser){
        SaleAnnouncement saleAnnouncement = saleAnnouncementService.getSaleAnnouncementById(id);
        model.addAttribute("announcement", saleAnnouncement);

        if (currentUser != null){
            model.addAttribute("authorized", "true");
            if (saleAnnouncement.getAuthor().equals(currentUser.getUsername()) ||
                    userService.getUserByUsername(currentUser.getUsername()).getRole() == Role.ADMIN){
                model.addAttribute("changeable", "true");
            }
        }
        return "oneSaleAnnounce";
    }


    @GetMapping("/announcements/newAnnouncement")
    @Operation(summary = "Returns creating announce page",
            description = "Allows to get creating announce page. Visible only for authorized users.")
    @PreAuthorize("hasAnyRole('ROLE_USER, ROLE_ADMIN')")
    public String getCreatingPage(@ModelAttribute("announcement") SaleAnnouncement saleAnnouncement){
        return "newAnnouncement";
    }


    @PostMapping("/announcements/newAnnouncement")
    @Operation(summary = "Post to server new announcement",
            description = "Allows to post new announcement. It takes current authorized user and creates an announcement")
    @PreAuthorize("hasAnyRole('ROLE_USER, ROLE_ADMIN')")
    public String createNewSaleAnnounce(@AuthenticationPrincipal @Parameter(description = "'on-line' user") UserDetails author,
                                        @ModelAttribute("announcement") @Valid SaleAnnouncement saleAnnouncement,
                                        BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            return "newAnnouncement";
        }
        saleAnnouncementService.createSaleAnnouncement(saleAnnouncement, userService.getUserByUsername(author.getUsername()));

        return "redirect:/";
    }


    @GetMapping("/announcements/{id}/edit")
    @Operation(summary = "Returns editing announce page",
            description = "Allows to get editing announce page. Visible only for authorized users.")
    @PreAuthorize("hasAnyRole('ROLE_USER, ROLE_ADMIN')")
    public String getEditingPage(@PathVariable("id") @Parameter(description = "ID of the announcement") String id,
                                 Model model){
        model.addAttribute("announcement", saleAnnouncementService.getSaleAnnouncementById(id));
        return "editAnnouncement";
    }


    @PatchMapping("/announcements/{id}")
    @Operation(summary = "Patch an existing announcement",
            description = "Allows to patch an announcement. Users can patch only their announces, admins can patch all of the announcements")
    @PreAuthorize("hasAnyRole('ROLE_USER, ROLE_ADMIN')")
    public String editSaleAnnouncement(@PathVariable("id") @Parameter(description = "ID of the announcement") String id,
                                       @ModelAttribute("announcement") @Valid SaleAnnouncement saleAnnouncement,
                                       BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            return "editAnnouncement";
        }
        saleAnnouncementService.patchSaleAnnouncement(saleAnnouncement, id);

        return "redirect:/";
    }


    @DeleteMapping("/announcements/{id}")
    @Operation(summary = "Delete an existing announcement",
            description = "Allows to delete an announcement. Users can delete only their announces, admins can delete all of the announcements")
    @PreAuthorize("hasAnyRole('ROLE_USER, ROLE_ADMIN')")
    public String deleteSaleAnnouncement(@PathVariable("id") @Parameter(description = "ID of the announcement") String id){
        saleAnnouncementService.deleteSaleAnnouncement(id);

        return "redirect:/";
    }
}
