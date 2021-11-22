package com.karpovskiy.autosales.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * This Entity represents Car Sale Announcement
 * The Entity is stored in @Table "CarSaleAnnouncements" and related to @Table "users" by author_id column
 *  */

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "CarSaleAnnouncements")
public class SaleAnnouncement {

    @Column(name = "num_of_announcements")
    private static int numOfAnnouncements;

    @Id
    @Column(name = "announcement_id")
    private String id;

    @Column(name = "publish_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date publishDate;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "brand")
    private String brand;

    @Column(name = "deleted")
    private boolean deleted;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "user_id")
    private User author;
}
