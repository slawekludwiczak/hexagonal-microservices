package com.ludigi.product.adapter.in.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
class RestSecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(
                        auth -> auth.anyRequest().authenticated()
                ).oauth2ResourceServer(
                        oauth2 -> oauth2.jwt(Customizer.withDefaults())
                ).build();
    }
}
