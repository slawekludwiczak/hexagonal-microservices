package com.ludigi.priceflow.frontend.offers;

import com.ludigi.priceflow.frontend.exception.NotFoundException;
import com.ludigi.priceflow.frontend.rest.client.OfferRestClient;
import com.ludigi.priceflow.frontend.rest.client.ProductRestClient;
import com.ludigi.priceflow.frontend.view.breadcrumbs.Link;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/offers")
class OffersController {
    private final OfferRestClient offerRestClient;
    private final ProductRestClient productRestClient;

    public OffersController(OfferRestClient offerRestClient, ProductRestClient productRestClient) {
        this.offerRestClient = offerRestClient;
        this.productRestClient = productRestClient;
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
        model.addAttribute("editOfferCommand", new OfferRestClient.EditOfferCommand(
                offer.selector(),
                offer.selectorType(),
                offer.pageType(),
                offer.refreshValue(),
                offer.refreshUnit()
        ));
        ProductRestClient.ProductResponse product = productRestClient
                .findById(offer.productId())
                .orElseThrow(NotFoundException::new);
        model.addAttribute("links", List.of(
                new Link(product.name(), "/products/%s".formatted(product.id())),
                new Link(offer.offerUrl(), "")
        ));
        return "offers/offer";
    }

    @PostMapping("/{id}/edit")
    String createOffer(@PathVariable("id") UUID offerId,
                       @ModelAttribute OfferRestClient.EditOfferCommand editOfferCommand) {
        offerRestClient.updateOffer(offerId, editOfferCommand);
        return "redirect:/offers/%s".formatted(offerId);
    }
}
