package com.ludigi.priceflow.frontend.config.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.stereotype.Component;

import java.time.Clock;

@Component
public class FeignClientInterceptor implements RequestInterceptor {
    private static final String CLIENT_REGISTRATION_ID = "keycloak";
    private final OAuth2AuthorizedClientManager authorizedClientManager;
    private final ClientRegistrationRepository clientRegistrationRepository;
    private final OAuth2AuthorizedClientService clientService;

    public FeignClientInterceptor(OAuth2AuthorizedClientManager authorizedClientManager,
                                  ClientRegistrationRepository clientRegistrationRepository,
                                  OAuth2AuthorizedClientService clientService) {
        this.authorizedClientManager = authorizedClientManager;
        this.clientRegistrationRepository = clientRegistrationRepository;
        this.clientService = clientService;
    }

    @Override
    public void apply(RequestTemplate requestTemplate) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ClientRegistration keycloak = clientRegistrationRepository.findByRegistrationId(CLIENT_REGISTRATION_ID);
        OAuth2AuthorizedClient authorizedClient = clientService.loadAuthorizedClient(keycloak.getRegistrationId(), authentication.getName());
        if (authorizedClient != null) {
            OAuth2AccessToken accessToken = authorizedClient.getAccessToken();
            if (accessToken == null || Clock.systemUTC().instant().isAfter(accessToken.getExpiresAt())) {
                OAuth2AuthorizeRequest authorizeRequest = OAuth2AuthorizeRequest
                        .withAuthorizedClient(authorizedClient)
                        .principal(authentication)
                        .build();
                OAuth2AuthorizedClient refreshedClient = authorizedClientManager.authorize(authorizeRequest);
                accessToken = refreshedClient.getAccessToken();
                System.out.println(refreshedClient.getAccessToken());
            }
            requestTemplate.header("Authorization", String.format("Bearer %s", accessToken.getTokenValue()));
        }
    }
}