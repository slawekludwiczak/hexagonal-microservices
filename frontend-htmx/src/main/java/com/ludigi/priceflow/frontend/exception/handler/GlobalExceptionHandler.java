package com.ludigi.priceflow.frontend.exception.handler;

import com.ludigi.priceflow.frontend.exception.ServiceUnavailableException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ServiceUnavailableException.class)
    String handleServiceUnavailable(ServiceUnavailableException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        model.addAttribute("timestamp", LocalDateTime.now());
        return "error";
    }
}
