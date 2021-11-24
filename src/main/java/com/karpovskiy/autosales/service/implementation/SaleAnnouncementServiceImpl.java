package com.karpovskiy.autosales.service.implementation;

import com.karpovskiy.autosales.generator.IDGenerator;
import com.karpovskiy.autosales.model.SaleAnnouncement;
import com.karpovskiy.autosales.model.User;
import com.karpovskiy.autosales.repository.SaleAnnouncementRepository;
import com.karpovskiy.autosales.service.SaleAnnouncementService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
        return saleAnnouncementRepository.findAllByDeletedIsFalse();
    }

    @Override
    public SaleAnnouncement getSaleAnnouncementById(String id) {
        return saleAnnouncementRepository.getById(id);
    }

    @Override
    public void createSaleAnnouncement(SaleAnnouncement saleAnnouncement, User author) {
        saleAnnouncement.setDeleted(false);
        saleAnnouncement.setAuthor(author.getUsername());
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
    public Page<SaleAnnouncement> getSaleAnnouncements(int pageNumber, String sort) {

        PageRequest pageRequest = PageRequest.of(pageNumber, 10);
        int total = saleAnnouncementRepository.findAllByDeletedIsFalse().size();
        int start = pageNumber*10;
        int end = Math.min(start + 10, total);
        List<SaleAnnouncement> result;

        switch (sort){
            case "old":
                List<SaleAnnouncement> saleAnnouncementsAscDate = saleAnnouncementRepository.findAllByDeletedIsFalse(Sort.by("publishDate").ascending());
                result = saleAnnouncementsAscDate.subList(start, end);
                return new PageImpl<>(
                        result, pageRequest, total
                );
            case "brand":
                Sort.Order order = new Sort.Order(Sort.Direction.ASC, "brand").ignoreCase();
                List<SaleAnnouncement> saleAnnouncementsAscBrand = saleAnnouncementRepository.findAllByDeletedIsFalse(Sort.by(order));
                result = saleAnnouncementsAscBrand.subList(start, end);
                return new PageImpl<>(
                        result, pageRequest, total
                );
            case "ascPrice":
                List<SaleAnnouncement> saleAnnouncementsAscPrice = saleAnnouncementRepository.findAllByDeletedIsFalse(Sort.by("price").ascending());
                result = saleAnnouncementsAscPrice.subList(start, end);
                return new PageImpl<>(
                        result, pageRequest, total
                );
            case "descPrice":
                List<SaleAnnouncement> saleAnnouncementsDescPrice = saleAnnouncementRepository.findAllByDeletedIsFalse(Sort.by("price").descending());
                result = saleAnnouncementsDescPrice.subList(start, end);
                return new PageImpl<>(
                        result, pageRequest, total
                );
            default:
                List<SaleAnnouncement> saleAnnouncementsDescDate = saleAnnouncementRepository.findAllByDeletedIsFalse(Sort.by("publishDate").descending());
                result = saleAnnouncementsDescDate.subList(start, end);
                return new PageImpl<>(
                        result, pageRequest, total
                );
        }

    }

    @Override
    public Integer getNumberOfPages(){
        Double numOfPages = ((double)saleAnnouncementRepository.findAllByDeletedIsFalse().size() / 10);
        return (int)Math.ceil(numOfPages);
    }


}
