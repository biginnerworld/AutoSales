package com.karpovskiy.autosales.repository;

import com.karpovskiy.autosales.model.SaleAnnouncement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface SaleAnnouncementRepository extends JpaRepository<SaleAnnouncement, String> {
    Page<SaleAnnouncement> findAllByDeletedIsFalse(Pageable pageable);
    List<SaleAnnouncement> findAllByDeletedIsFalse();
}
