package com.ludigi.priceflow.frontend.offers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Controller
@RequestMapping("/offers")
public class OffersController {
    private final OfferRestClient offerRestClient;

    public OffersController(OfferRestClient offerRestClient) {
        this.offerRestClient = offerRestClient;
    }

    @GetMapping("")
    String getOffers() {
        return "offers/offers";
    }

    @PostMapping("/add")
    String createOffer(@RequestParam String offerUrl,
                       @RequestParam String productId) {
        OfferRestClient.CreateOfferCommand createOfferCommand = new OfferRestClient.CreateOfferCommand(offerUrl, productId,
                "div[data-testid=ad-price-container] > h3", "CSS", "HTML", 1, ChronoUnit.HOURS.name());
        offerRestClient.createOffer(createOfferCommand);
        return "redirect:/offers";
    }
}
