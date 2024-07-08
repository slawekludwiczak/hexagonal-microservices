package com.ludigi.priceflow.frontend.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Component;

@Component
public class FeignClientInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof OAuth2AuthenticationToken oauth2Token) {
            DefaultOidcUser oidcUser = (DefaultOidcUser) oauth2Token.getPrincipal();
            OidcIdToken idToken = oidcUser.getIdToken();
            requestTemplate.header("Authorization", String.format("Bearer %s", idToken.getTokenValue()));
        }
    }
}