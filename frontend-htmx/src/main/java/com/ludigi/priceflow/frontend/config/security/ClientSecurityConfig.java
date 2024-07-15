package com.ludigi.priceflow.frontend.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ClientSecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                        requests -> requests
                                .requestMatchers("/").authenticated()
                                .requestMatchers("/products").authenticated()
                                .requestMatchers("/offers").authenticated()
                                .anyRequest().permitAll()
                )
                .oauth2Login(Customizer.withDefaults());
        return http.build();
    }
}