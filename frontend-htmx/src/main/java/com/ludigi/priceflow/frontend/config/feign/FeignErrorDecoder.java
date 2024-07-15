package com.ludigi.priceflow.frontend.config.feign;

import com.ludigi.priceflow.frontend.exception.InternalServerErrorException;
import com.ludigi.priceflow.frontend.exception.ServiceUnavailableException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;

@Configuration
public class FeignErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        int status = response.status();
        if (status == HttpStatus.UNAUTHORIZED.value()) {
            throw new OAuth2AuthenticationException("Unauthorized access to %s".formatted(response.request().url()));
        } else if (status == HttpStatus.SERVICE_UNAVAILABLE.value()) {
            return new ServiceUnavailableException("Service unavailable %s".formatted(response.request().url()));
        } else if (status == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
            return new InternalServerErrorException("Internal server error %s".formatted(response.request().url()));
        }
        return null;
    }
}
