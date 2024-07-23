package com.ludigi.priceflow.frontend.offers;

import com.ludigi.priceflow.frontend.exception.NotFoundException;
import com.ludigi.priceflow.frontend.rest.client.OfferRestClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @GetMapping("/{id}")
    String getOffer(@PathVariable("id") String id, Model model) {
        OfferRestClient.OfferResponse offer = offerRestClient.findOfferById(id).orElseThrow(NotFoundException::new);
        OfferRestClient.PriceHistoryResponse offerPriceHistory = offerRestClient.findPriceHistoryByOfferId(id);
        model.addAttribute("offer", offer);
        model.addAttribute("priceHistory", offerPriceHistory);
        model.addAttribute("history", offerPriceHistory.getChart());
        return "offers/offer";
    }
}
