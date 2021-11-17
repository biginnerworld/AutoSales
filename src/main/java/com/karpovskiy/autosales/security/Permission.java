package com.karpovskiy.autosales.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Permission {
    USER_WRITE("user:write"),
    ADMIN_WRITE("admin_write");

    private final String permission;
}
