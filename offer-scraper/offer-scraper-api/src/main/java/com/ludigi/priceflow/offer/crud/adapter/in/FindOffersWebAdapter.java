package com.ludigi.priceflow.offer.crud.adapter.in;

import com.ludigi.priceflow.offer.crud.port.in.FindOfferUseCase;
import com.ludigi.priceflow.offer.crud.port.in.FindOffersUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
public class FindOffersWebAdapter {
    private final FindOffersUseCase findOffersUseCase;
    private final FindOfferUseCase findOfferUseCase;

    public FindOffersWebAdapter(FindOffersUseCase findOffersUseCase, FindOfferUseCase findOfferUseCase) {
        this.findOffersUseCase = findOffersUseCase;
        this.findOfferUseCase = findOfferUseCase;
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

    @GetMapping("/api/offers/{id}")
    OfferResponse findOffersByProductId(@PathVariable("id") UUID offerId) {
        return findOfferUseCase.findById(offerId)
                .map(offer -> new OfferResponse(
                        offer.getId(),
                        offer.getUrl().url(),
                        offer.getProductId().value(),
                        offer.getSelector().selector(),
                        offer.getSelector().type().name(),
                        offer.getPageType().name(),
                        offer.getRefreshPeriod().value(),
                        offer.getRefreshPeriod().unit().name()
                )).orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
                );
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
