package com.karpovskiy.autosales.service;

import com.karpovskiy.autosales.model.SaleAnnouncement;
import com.karpovskiy.autosales.model.User;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * This interface describes CRUD methods for SaleAnnouncementServiceImpl class
 * */

public interface SaleAnnouncementService {

    List<SaleAnnouncement> getAllAnnouncements();

    void createSaleAnnouncement(SaleAnnouncement saleAnnouncement, User author);

    SaleAnnouncement getSaleAnnouncementById(String id);

    void patchSaleAnnouncement(SaleAnnouncement saleAnnouncement, String id);

    void deleteSaleAnnouncement(String id);

    Page<SaleAnnouncement> getSaleAnnouncements(int pageNumber, String sort);

    Integer getNumberOfPages();

}
