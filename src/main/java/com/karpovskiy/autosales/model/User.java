package com.karpovskiy.autosales.model;

import com.karpovskiy.autosales.security.Role;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * This Entity represents User
 * Each User has a role(user or admin)
 * */

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @Column(name = "user_id")
    private String id;

    @Column(name = "username")
    @NotBlank(message = "Username should not be empty")
    @Length(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String username;

    @Column(name = "password")
    @NotBlank(message = "Password should not be empty")
    @Length(min = 6, message = "Password should be longer than 6")
    private String password;

    @Column(name = "user_role")
    private Role role;

    @Column(name = "email")
    @NotBlank(message = "E-mail should not be empty")
    @Email(message = "Incorrect e-mail")
    private String email;

    @Column(name = "isDeleted")
    private boolean isDeleted;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "author")
    @ToString.Exclude
    private List<SaleAnnouncement> announcements;
}
