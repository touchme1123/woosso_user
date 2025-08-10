package com.shpark.woosso.api.user.dto;

import com.shpark.woosso.api.user.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
public class CustomUserDetails implements UserDetails {

    private final User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return Collections.singletonList(new SimpleGrantedAuthority(user.getRole()));
    }

    @Override
    public String getPassword() {
        return user.getUserPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserId();
    }
}
