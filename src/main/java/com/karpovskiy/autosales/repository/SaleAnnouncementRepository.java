package com.karpovskiy.autosales.repository;

import com.karpovskiy.autosales.model.SaleAnnouncement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface SaleAnnouncementRepository extends JpaRepository<SaleAnnouncement, String> {
    void deleteSaleAnnouncementById(String id);
}
