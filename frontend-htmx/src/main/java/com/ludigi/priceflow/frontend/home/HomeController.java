package com.ludigi.priceflow.frontend.home;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final Counter counter;

    public HomeController(MeterRegistry meterRegistry) {
        this.counter = Counter.builder("views").register(meterRegistry);
    }

    @GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    String home() {
        counter.increment();
        return "index";
    }
}
