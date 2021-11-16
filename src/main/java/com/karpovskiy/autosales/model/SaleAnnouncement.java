package com.karpovskiy.autosales.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

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

    @Column(name = "deleted")
    private boolean deleted;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "user_id")
    private User author;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SaleAnnouncement that = (SaleAnnouncement) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
