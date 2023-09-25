package org.example.security;

import org.example.entity.Client;
import org.example.enums.Status;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public  class SecurityEntity implements UserDetails {

    private final String login;
    private final String password;
    private final List<SimpleGrantedAuthority> authority;
    private final boolean isActive;

    public SecurityEntity(String login, String password, List<SimpleGrantedAuthority> authority, boolean isActive) {
        this.login = login;
        this.password = password;
        this.authority = authority;
        this.isActive = isActive;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authority;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActive;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }
}
