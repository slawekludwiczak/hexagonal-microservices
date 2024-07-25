package com.ludigi.priceflow.frontend.home;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class HomeController {
    @GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    String home() {
        return "index";
    }
}
