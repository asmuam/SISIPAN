/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.polstat.sisipan.auth;

import com.polstat.sisipan.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *
 * @author asmuammal
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtTokenFilter;
    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder)
            throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http
                .getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
        return authenticationManagerBuilder.build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.csrf().disable();
        http.authorizeRequests()
//                .requestMatchers("/register", "/login","/docs/**").permitAll()
//                .requestMatchers(HttpMethod.POST,"/formasi/**").hasRole("ADMIN")
//                .requestMatchers(HttpMethod.PATCH,"/formasi/**").hasRole("ADMIN")
//                .requestMatchers(HttpMethod.PUT,"/formasi/**").hasRole("ADMIN")
//                .requestMatchers(HttpMethod.DELETE,"/formasi/**").hasRole("ADMIN")
//                .requestMatchers(HttpMethod.POST,"/mahasiswa/**").hasRole("ADMIN")
//                .requestMatchers(HttpMethod.PATCH,"/mahasiswa/**").hasRole("ADMIN")
//                .requestMatchers(HttpMethod.PUT,"/mahasiswa/**").hasRole("ADMIN")
//                .requestMatchers(HttpMethod.DELETE,"/mahasiswa/**").hasRole("ADMIN")
//                .requestMatchers("/provinsi/**").hasRole("ADMIN")
//                .requestMatchers(HttpMethod.POST,"/pilihan/**").hasRole("MAHASISWA")
//                .anyRequest().authenticated();
                  .anyRequest().permitAll();
        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
