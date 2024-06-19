package com.ludigi.product.adapter.in.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.oauth2.core.authorization.OAuth2AuthorizationManagers.hasScope;

@Configuration
class RestSecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers("/api/hello").access(hasScope("products.read"))
                                .anyRequest().authenticated()
                ).oauth2ResourceServer(
                        oauth2 -> oauth2.jwt(Customizer.withDefaults())
                ).csrf(
                        AbstractHttpConfigurer::disable
                ).build();
    }
}
