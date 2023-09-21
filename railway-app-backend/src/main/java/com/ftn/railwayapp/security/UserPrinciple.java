package com.ftn.railwayapp.security;

import com.ftn.railwayapp.response.UserSecurityResponse;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserPrinciple implements UserDetails {

    private final UserSecurityResponse userSecurityResponse;

    public UserPrinciple(UserSecurityResponse userSecurityResponse) {
        this.userSecurityResponse = userSecurityResponse;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(userSecurityResponse.role().getAuthority()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return userSecurityResponse.password();
    }

    @Override
    public String getUsername() {
        return userSecurityResponse.email();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public UserSecurityResponse getUser() {
        return userSecurityResponse;
    }

}
