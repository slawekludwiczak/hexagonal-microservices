package com.ludigi.priceflow.frontend.config.feign;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;

public class FeignErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        if (response.status() == HttpStatus.UNAUTHORIZED.value()) {
            throw new OAuth2AuthenticationException("unauthorized");
        }
        return null;
    }
}
