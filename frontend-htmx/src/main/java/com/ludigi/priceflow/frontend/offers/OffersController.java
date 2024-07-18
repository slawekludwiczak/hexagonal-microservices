package com.ludigi.priceflow.frontend.offers;

import com.ludigi.priceflow.frontend.rest.client.OfferRestClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/offers")
class OffersController {
    private final OfferRestClient offerRestClient;

    public OffersController(OfferRestClient offerRestClient) {
        this.offerRestClient = offerRestClient;
    }

    @GetMapping("")
    String getOffers() {
        return "offers/offers";
    }

    @PostMapping("/add")
    String createOffer(@ModelAttribute OfferRestClient.CreateOfferCommand createOfferCommand) {
        offerRestClient.createOffer(createOfferCommand);
        return "redirect:/products/%s".formatted(createOfferCommand.productId());
    }
}
