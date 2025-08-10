package com.shpark.woosso.api.user.jwt;

import com.shpark.woosso.api.user.domain.User;
import com.shpark.woosso.api.user.dto.CustomUserDetails;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@AllArgsConstructor
public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtill;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorization = request.getHeader("Authorization");

        //Authorization 헤더 검증
        if(authorization == null || !authorization.startsWith("Bearer ")) {

            System.out.println("token null");
            filterChain.doFilter(request, response);

            return;
        }

        String token = authorization.split(" ")[1];

        //토큰 만료 체크
        try {
            jwtUtill.isExpired(token);

        } catch(ExpiredJwtException e) {
            System.out.println("token expired");
            filterChain.doFilter(request, response);

            return;
        }

        //token 에서 데이터 꺼내오기
        String userId = jwtUtill.getUsername(token);
        String role = jwtUtill.getRole(token);

        //user 객체 생성
        User user = new User();
        user.setUserId(userId);
        user.setUserPassword("password");
        user.setRole(role);

        //userdetail 객체 생성
        CustomUserDetails customUserDetails = new CustomUserDetails(user);

        System.out.println(customUserDetails.getAuthorities());
        customUserDetails.getAuthorities();

        Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);





    }
}
