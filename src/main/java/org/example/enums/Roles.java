package org.example.enums;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Roles  {

    ADMIN(Set.of(Permissions.PERMISSIONS_ADMIN)),
    USER(Set.of(Permissions.PERMISSIONS_USER)),
    MANAGER(Set.of(Permissions.PERMISSIONS_MANAGER));

    private final Set<Permissions> permissions;

    Roles(Set<Permissions> permissions) {
        this.permissions = permissions;
    }

    public Set<Permissions> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities(){
        return getPermissions().stream()
                .map(permissions1 -> new SimpleGrantedAuthority(permissions1.getPermission()))
                .collect(Collectors.toSet());
    }
}
