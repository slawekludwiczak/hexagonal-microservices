package com.ludigi.priceflow.offer.crud.adapter.in;

import com.ludigi.priceflow.offer.crud.port.in.FindOffersUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class FindOffersWebAdapter {
    private final FindOffersUseCase findOffersUseCase;

    public FindOffersWebAdapter(FindOffersUseCase findOffersUseCase) {
        this.findOffersUseCase = findOffersUseCase;
    }

    @GetMapping("/api/offers")
    OffersResponse findOffersByProductId(@RequestParam("productId") String productId) {
        List<OfferResponse> productOffers = findOffersUseCase.findAllByProductId(productId)
                .stream()
                .map(offer -> new OfferResponse(
                        offer.getId(),
                        offer.getUrl().url(),
                        offer.getProductId().value(),
                        offer.getSelector().selector(),
                        offer.getSelector().type().name(),
                        offer.getPageType().name(),
                        offer.getRefreshPeriod().value(),
                        offer.getRefreshPeriod().unit().name()
                )).toList();
        return new OffersResponse(productOffers);
    }

    record OfferResponse(UUID id,
                         String offerUrl,
                         String productId,
                         String selector,
                         String selectorType,
                         String pageType,
                         long refreshValue,
                         String refreshUnit) {
    }

    record OffersResponse(List<OfferResponse> offers) {
    }
}
