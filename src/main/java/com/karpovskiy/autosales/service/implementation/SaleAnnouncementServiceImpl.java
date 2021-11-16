package com.karpovskiy.autosales.service.implementation;

import com.karpovskiy.autosales.generator.IDGenerator;
import com.karpovskiy.autosales.model.SaleAnnouncement;
import com.karpovskiy.autosales.model.User;
import com.karpovskiy.autosales.repository.SaleAnnouncementRepository;
import com.karpovskiy.autosales.service.SaleAnnouncementService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
        saleAnnouncement.setDeleted(false);
        saleAnnouncement.setAuthor(author);
        saleAnnouncement.setId(IDGenerator.generateID());
        saleAnnouncement.setPublishDate(new Date());
        saleAnnouncementRepository.save(saleAnnouncement);
    }

    @Override
    public void patchSaleAnnouncement(SaleAnnouncement editedSaleAnnouncement, String id) {
        SaleAnnouncement saleAnnouncement = getSaleAnnouncementById(id);

        saleAnnouncement.setTitle(editedSaleAnnouncement.getTitle());
        saleAnnouncement.setDescription(editedSaleAnnouncement.getDescription());
        saleAnnouncement.setPrice(editedSaleAnnouncement.getPrice());

        saleAnnouncementRepository.save(saleAnnouncement);
    }

    @Override
    public void deleteSaleAnnouncement(String id) {
        SaleAnnouncement saleAnnouncement = getSaleAnnouncementById(id);

        saleAnnouncement.setDeleted(true);

        saleAnnouncementRepository.save(saleAnnouncement);
    }

    @Override
    public Page<SaleAnnouncement> getSaleAnnouncements(int pageNumber) {

       return saleAnnouncementRepository.findAllByDeletedIsFalse(
                PageRequest.of(pageNumber, 10, Sort.by(Sort.Direction.DESC, "publishDate"))
        );

    }

    @Override
    public Integer getNumberOfPages(){
        Double numOfPages = ((double)saleAnnouncementRepository.findAll().size() / 10);
        return (int)Math.ceil(numOfPages);
    }
}
