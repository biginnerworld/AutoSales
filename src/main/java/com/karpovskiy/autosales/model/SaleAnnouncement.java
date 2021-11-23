package com.karpovskiy.autosales.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * This Entity represents Car Sale Announcement
 * The Entity is stored in @Table "CarSaleAnnouncements" and related to @Table "users" by author_id column
 *  */

@Entity
@Schema(description = "Sale Announcement Entity")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "CarSaleAnnouncements")
public class SaleAnnouncement {

    @Id
    @Schema(description = "Identifier")
    @Column(name = "announcement_id")
    private String id;

    @Column(name = "publish_date")
    @Schema(description = "Date of Publishing")
    @Temporal(TemporalType.TIMESTAMP)
    private Date publishDate;

    @Column(name = "title")
    @Schema(description = "Title of the Announcement")
    @NotBlank(message = "Title should not be empty")
    @Length(min = 3, max = 50, message = "the length of the title should be between 3 and 50")
    private String title;

    @Column(name = "description")
    @Schema(description = "Description")
    @NotBlank(message = "Description should not be empty")
    @Length(min = 3, max = 200, message = "the length of the description should be between 3 and 200")
    private String description;

    @Column(name = "price")
    @Schema(description = "Price")
    @NotNull(message = "Price should not be empty")
    @DecimalMin(value = "0.0", message = "Price should be more than 0")
    private BigDecimal price;

    @Column(name = "brand")
    @Schema(description = "Brand")
    @NotBlank(message = "Brand should not be empty")
    @Length(min = 3, max = 20, message = "the length of the brand should be between 3 and 20")
    private String brand;

    @Column(name = "deleted")
    private boolean deleted;

    @Schema(description = "Author of the Announcement")
    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "user_id")
    private User author;
}
