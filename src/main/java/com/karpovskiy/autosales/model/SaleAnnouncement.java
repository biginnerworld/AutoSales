package com.karpovskiy.autosales.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * This Entity represents Car Sale Announcement
 * The Entity is stored in @Table "CarSaleAnnouncements" and related to @Table "users" by author_id column
 *  */

@Entity
@Data
@Table(name = "CarSaleAnnouncements")
public class SaleAnnouncement {

    @Id
    @Column(name = "announcement_id")
    private String id;

    @Column(name = "publish_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date publishDate;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "user_id")
    private User author;
}
