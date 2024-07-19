package com.ludigi.priceflow.offer.crud.adapter.in;

import com.ludigi.priceflow.offer.crud.OfferPrice;
import com.ludigi.priceflow.offer.crud.port.in.GetPriceHistoryUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class GetPriceHistoryWebAdapter {
    private final GetPriceHistoryUseCase getPriceHistoryUseCase;

    public GetPriceHistoryWebAdapter(GetPriceHistoryUseCase getPriceHistoryUseCase) {
        this.getPriceHistoryUseCase = getPriceHistoryUseCase;
    }

    @GetMapping("/api/offers/{id}/prices")
    ResponseEntity<?> getPriceHistoryByOffer(@PathVariable("id") UUID offerId) {
        List<OfferPrice> priceHistory = getPriceHistoryUseCase.findPriceHistory(offerId);
        return ResponseEntity.ok(new PriceHistoryResponse(priceHistory));
    }

    private record PriceHistoryResponse(List<OfferPrice> prices) {}
}
