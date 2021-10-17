package org.example.ordersmanager.security;

import org.apache.commons.compress.utils.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static org.example.ordersmanager.security.UserPermission.*;

public enum UserRole {
    USER(Sets.newHashSet(LIST_OWN_ORDERS, CREATE_ORDER)),
    OPERATOR(Sets.newHashSet(LIST_ALL_ORDERS)),
    ADMIN(Sets.newHashSet(LIST_ALL_ORDERS, DELETE_ANY_ORDER));

    private final Set<UserPermission> permissions;

    UserRole(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<UserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
