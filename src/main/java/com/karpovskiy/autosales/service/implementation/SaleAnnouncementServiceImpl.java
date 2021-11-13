package com.karpovskiy.autosales.service.implementation;

import com.karpovskiy.autosales.generator.IDGenerator;
import com.karpovskiy.autosales.model.SaleAnnouncement;
import com.karpovskiy.autosales.model.User;
import com.karpovskiy.autosales.repository.SaleAnnouncementRepository;
import com.karpovskiy.autosales.service.SaleAnnouncementService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class SaleAnnouncementServiceImpl implements SaleAnnouncementService {

    private final SaleAnnouncementRepository saleAnnouncementRepository;


    @Override
    public List<SaleAnnouncement> getAllAnnouncements() {
        return saleAnnouncementRepository.findAll();
    }

    @Override
    public SaleAnnouncement getSaleAnnouncementById(String id) {
        return saleAnnouncementRepository.getById(id);
    }

    @Override
    public void createSaleAnnouncement(SaleAnnouncement saleAnnouncement, User author) {
        saleAnnouncement.setAuthor(author);
        saleAnnouncement.setId(IDGenerator.generateID());
        saleAnnouncement.setPublishDate(new Date());
        saleAnnouncementRepository.save(saleAnnouncement);
    }

    @Override
    public void patchSaleAnnouncement(SaleAnnouncement editedSaleAnnouncement, String id) {
        SaleAnnouncement saleAnnouncement = getSaleAnnouncementById(id);

        saleAnnouncement.setBrand(editedSaleAnnouncement.getBrand());
        saleAnnouncement.setModel(editedSaleAnnouncement.getModel());
        saleAnnouncement.setDescription(editedSaleAnnouncement.getDescription());
        saleAnnouncement.setPrice(editedSaleAnnouncement.getPrice());

        saleAnnouncementRepository.save(saleAnnouncement);
    }

    @Override
    public void deleteSaleAnnouncement(String id) {
        saleAnnouncementRepository.deleteSaleAnnouncementById(id);
    }
}