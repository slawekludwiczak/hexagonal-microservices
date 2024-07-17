package com.ludigi.priceflow.frontend.home;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final Logger LOG = LoggerFactory.getLogger(HomeController.class);
    private final Counter counter;

    public HomeController(MeterRegistry meterRegistry) {
        this.counter = Counter.builder("views").register(meterRegistry);
    }

    @GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    String home(HttpServletRequest request) {
        counter.increment();
        LOG.info("Homepage viewed by " + request.getRemoteAddr());
        return "index";
    }
}
