/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.polstat.sisipan.auth;

import com.polstat.sisipan.dto.UserDto;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 *
 * @author asmuammal
 */
@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if (!hasAuthorizationBearer(request)) {
            filterChain.doFilter(request, response);
            return;
        }
        System.out.println("dofilter");
        String token = getAccessToken(request);
        if (!jwtUtil.validateAccessToken(token)) {
            System.out.println("token invalid" + token);
            filterChain.doFilter(request, response);
            return;
        }
        System.out.println("token valid" + token);
        setAuthenticationContext(token, request);
        filterChain.doFilter(request, response);
    }

    private boolean hasAuthorizationBearer(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        System.out.println("header" + header);
        if (ObjectUtils.isEmpty(header) || !header.startsWith("Bearer")) {
            System.out.println("gagal" + header);

            return false;
        }
        System.out.println("sukses" + header);

        return true;
    }

    public String getAccessToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        String token = header.split(" ")[1].trim();
        return token;
    }

    private void setAuthenticationContext(String token, HttpServletRequest request) {
        UserDetails userDetails = getUserDetails(token);
        System.out.println("userdetails" + userDetails);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
                userDetails.getAuthorities()); // Menggunakan peran dari userDetails
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private UserDetails getUserDetails(String token) {
        Claims claims = jwtUtil.parseClaims(token);
        System.out.println("claim" + claims);

        String subject = claims.getSubject();
        System.out.println("claim subject" + subject);

        Collection<? extends GrantedAuthority> authorities = authoritiesFromClaims(claims);
        System.out.println("authorities" + authorities);
        UserDetails userDetails = new UserDto(subject, authorities);
        System.out.println("new userdetails" + userDetails);
        return userDetails;
    }

//    private Collection<? extends GrantedAuthority> authoritiesFromClaims(Claims claims) {
//        @SuppressWarnings("unchecked")
//        List<Map<String, String>> authoritiesMap = (List<Map<String, String>>) claims.get("authorities");
//
//        if (authoritiesMap == null) {
//            return Collections.emptyList();
//        }
//
//        return authoritiesMap.stream()
//                .map(authorityData -> new SimpleGrantedAuthority("ROLE_" + authorityData.get("authority")))
//                .collect(Collectors.toList());
//    }
    private Collection<? extends GrantedAuthority> authoritiesFromClaims(Claims claims) {
        Object authoritiesObject = claims.get("authorities");

        if (authoritiesObject instanceof List<?>) {
            @SuppressWarnings("unchecked")
            List<String> authoritiesList = (List<String>) authoritiesObject;
            System.out.println("authList" + authoritiesList);
            if (!authoritiesList.isEmpty()) {
                String authority = authoritiesList.get(0);
                System.out.println("authList" + authority);
                return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + authority));
            }
        }

        return Collections.emptyList();
    }

    public boolean validateUser(Long id, HttpServletRequest request) {
        Long userIdFromToken = jwtUtil.getUserIdFromToken(getAccessToken(request));
        if (userIdFromToken == null || userIdFromToken != id) {
            return false;
        }
        return true;
    }
}
