package com.karpovskiy.autosales.security;

import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

import static com.karpovskiy.autosales.security.Permission.*;

/**
 * Enum of User's roles
 * */

@AllArgsConstructor
@Getter
public enum Role {
    USER(Sets.newHashSet(USER_WRITE)),
    ADMIN(Sets.newHashSet(USER_WRITE, ADMIN_WRITE));

    private final Set<Permission> permissions;

}
