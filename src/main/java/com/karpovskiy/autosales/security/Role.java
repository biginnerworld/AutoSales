package com.karpovskiy.autosales.security;

import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
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

    public Set<SimpleGrantedAuthority> getGrantedAuthority(){
        Set<SimpleGrantedAuthority> permissions = new HashSet<>();

        for (Permission permission : getPermissions()){
            permissions.add(new SimpleGrantedAuthority(permission.getPermission()));
        }
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

        return permissions;
    }
}
