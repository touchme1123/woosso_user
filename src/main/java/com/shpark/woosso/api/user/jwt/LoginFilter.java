package com.shpark.woosso.api.user.jwt;

import com.shpark.woosso.api.user.dto.CustomUserDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    private final JWTUtil jwtUtill;

    public LoginFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtill) {
        super(authenticationManager);

        this.authenticationManager = authenticationManager;
        this.jwtUtill = jwtUtill;

        setUsernameParameter("userId"); //기본 username 이 아닌 경우 세팅
        setPasswordParameter("userPassword"); //기본 password 가 아닌 경우 세팅
        setFilterProcessesUrl("/user/login"); // 기본 경로 /login 이 아닌 경우 세팅

    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        String contentType = request.getContentType();
        System.out.println(contentType);

        String userId = obtainUsername(request);
        String password = obtainPassword(request);

        System.out.println(userId);
        System.out.println(password);

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userId, password, null);

        return authenticationManager.authenticate(authToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {

        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

        String userId = customUserDetails.getUsername();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();

        GrantedAuthority auth = iterator.next();

        String role = auth.getAuthority();

        String token = jwtUtill.createJwt(userId, role, 1000*60*60*10L);
        response.addHeader("Authorization", "Bearer " + token);
    }

    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {

        response.setStatus(401);
    }
}
