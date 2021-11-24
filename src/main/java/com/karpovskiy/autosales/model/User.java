package com.karpovskiy.autosales.model;

import com.karpovskiy.autosales.security.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * This Entity represents User
 * Each User has a role(user or admin)
 * */

@Entity
@Schema(description = "User Entity")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @Column(name = "user_id")
    @Schema(description = "Identifier")
    private String id;

    @Column(name = "username")
    @Schema(description = "Username", example = "Klon")
    @NotBlank(message = "Username should not be empty")
    @Length(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String username;

    @Column(name = "password")
    @Schema(description = "Password", example = "qwerty1234")
    @NotBlank(message = "Password should not be empty")
    @Length(min = 6, message = "Password should be longer than 6")
    private String password;

    @Column(name = "user_role")
    @Schema(description = "Role")
    private Role role;

    @Column(name = "email")
    @Schema(description = "E-mail", example = "qweqrty1234@mail.ru")
    @NotBlank(message = "E-mail should not be empty")
    @Email(message = "Incorrect e-mail")
    private String email;

    @Column(name = "isDeleted")
    private boolean isDeleted;
}
