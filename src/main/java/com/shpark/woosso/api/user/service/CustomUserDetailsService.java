package com.shpark.woosso.api.user.service;

import com.shpark.woosso.api.user.domain.User;
import com.shpark.woosso.api.user.dto.CustomUserDetails;
import com.shpark.woosso.api.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        User userData = userRepository.findByUserId(userId);

        if(userData != null) {
            return new CustomUserDetails(userData);
        }

        return null;
    }
}
