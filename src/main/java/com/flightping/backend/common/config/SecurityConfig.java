package com.flightping.backend.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // Swagger
                        .requestMatchers("/swagger-ui/**", "/api-docs/**", "/swagger-ui.html").permitAll()
                        // Actuator
                        .requestMatchers("/actuator/**", "/api/health").permitAll()
                        // 내부 API
                        .requestMatchers(HttpMethod.POST, "/api/internal/**").permitAll()
                        // 공개 API
                        .requestMatchers(HttpMethod.GET, "/api/v1/deals/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/airports").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/destinations").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/routes/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/flights").permitAll()
                        // 인증 필요 API (X-User-Id 헤더 필요)
                        .anyRequest().authenticated()
                );

        return http.build();
    }
}
