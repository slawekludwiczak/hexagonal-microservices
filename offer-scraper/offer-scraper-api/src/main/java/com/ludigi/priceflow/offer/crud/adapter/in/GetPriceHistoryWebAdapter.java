package com.ludigi.priceflow.offer.crud.adapter.in;

import com.ludigi.priceflow.offer.common.vo.Currency;
import com.ludigi.priceflow.offer.common.vo.Price;
import com.ludigi.priceflow.offer.crud.OfferPrice;
import com.ludigi.priceflow.offer.crud.port.in.GetPriceHistoryUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
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

    //TODO remove
    @GetMapping("/api/offers/mock/prices")
    ResponseEntity<?> getPriceHistoryByOfferMock(@CurrentSecurityContext(expression = "authentication.principal") Jwt jwt) {
        List<OfferPrice> priceHistory = List.of(
                new OfferPrice(new Price(12.345, Currency.PLN), LocalDateTime.now()),
                new OfferPrice(new Price(23.456, Currency.PLN), LocalDateTime.now()),
                new OfferPrice(new Price(34.567, Currency.PLN), LocalDateTime.now())
        );
        return ResponseEntity.ok(new PriceHistoryResponse(priceHistory));
    }

    //TODO remove
    @GetMapping("/api/offers/mock/prices-authenticated")
    ResponseEntity<?> getPriceHistoryByOfferMockAuth(@CurrentSecurityContext(expression = "authentication.principal") Jwt jwt) {
        List<OfferPrice> priceHistory = List.of(
                new OfferPrice(new Price(12.345, Currency.PLN), LocalDateTime.now()),
                new OfferPrice(new Price(23.456, Currency.PLN), LocalDateTime.now()),
                new OfferPrice(new Price(34.567, Currency.PLN), LocalDateTime.now())
        );
        return ResponseEntity.ok(new PriceHistoryResponse(priceHistory));
    }

    private record PriceHistoryResponse(List<OfferPrice> prices) {}
}
