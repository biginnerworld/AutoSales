package com.karpovskiy.autosales.model;

import com.karpovskiy.autosales.security.Role;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * This Entity represents User
 * Each User has a role(user or admin)
 * */

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @Column(name = "user_id")
    private String id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "user_role")
    private Role role;

    @Column(name = "email")
    private String email;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "author")
    private List<SaleAnnouncement> announcements;
}
