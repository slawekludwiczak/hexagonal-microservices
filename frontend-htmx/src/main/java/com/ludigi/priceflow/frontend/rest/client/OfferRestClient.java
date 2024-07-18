package com.ludigi.priceflow.frontend.rest.client;

import com.ludigi.priceflow.frontend.config.feign.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@FeignClient(value = "priceflow-offer-scraper", configuration = {FeignConfiguration.class})
public interface OfferRestClient {
    @GetMapping("/api/offers/mock/prices")
    PriceHistoryResponse findAllMock();
    @PostMapping("/api/offers")
    void createOffer(CreateOfferCommand command);
    @GetMapping("/api/offers")
    OffersResponse findOffersByProductId(@RequestParam("productId") String productId);

    record PriceHistoryResponse(List<OfferPrice> prices) {}

    record OfferPrice(Price price, LocalDateTime time) {
    }

    record Price(double value, Currency currency) {
    }

    enum Currency {
        NONE,
        PLN
    }

    record OffersResponse(List<OfferResponse> offers) { }
    record OfferResponse(UUID id,
                         String offerUrl,
                         String productId,
                         String selector,
                         String selectorType,
                         String pageType,
                         int refreshValue,
                         String refreshUnit) {
    }

    record CreateOfferCommand(
            String offerUrl,
            String productId,
            String selector,
            String selectorType,
            String pageType,
            int refreshValue,
            String refreshUnit
    ) { }
}
