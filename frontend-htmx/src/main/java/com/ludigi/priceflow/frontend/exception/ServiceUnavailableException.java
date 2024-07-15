package com.ludigi.priceflow.frontend.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

public class ServiceUnavailableException extends RuntimeException {
    public ServiceUnavailableException(String message) {
        super(message);
    }
}
