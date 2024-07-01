package com.ludigi.priceflow.offer.crud.adapter.in.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.oauth2.core.authorization.OAuth2AuthorizationManagers.hasScope;

@Configuration
public class SecurityConfig {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers(HttpMethod.POST,"/api/offers").access(hasScope("products.write"))
                                .requestMatchers(HttpMethod.GET,"/api/offers/{id}/prices").access(hasScope("products.read"))
                                .requestMatchers(HttpMethod.GET,"/actuator/health").permitAll()
                                .requestMatchers(PathRequest.toH2Console()).permitAll()
                                .anyRequest().authenticated()
                ).oauth2ResourceServer(
                        oauth2 -> oauth2.jwt(Customizer.withDefaults())
                ).csrf(
                        AbstractHttpConfigurer::disable
                ).headers(
                     config -> config.frameOptions(
                             HeadersConfigurer.FrameOptionsConfig::sameOrigin
                     )
                ).build();
    }
}
