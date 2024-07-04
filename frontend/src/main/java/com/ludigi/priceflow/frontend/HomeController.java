package com.ludigi.priceflow.frontend;

import org.springframework.http.MediaType;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Controller
public class HomeController {
    private final RestTemplate restTemplate;

    public HomeController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    String home(@RegisteredOAuth2AuthorizedClient("keycloak") OAuth2AuthorizedClient authorizedClient,
                Model model) {
        String prices = restTemplate.getForObject("http://priceflow-offer-scraper/api/offers/mock/prices-authenticated", String.class);
        model.addAttribute("prices", prices);
        return "index";
    }
}
