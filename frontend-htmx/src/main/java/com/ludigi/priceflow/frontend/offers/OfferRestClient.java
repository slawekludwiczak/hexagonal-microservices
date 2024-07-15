package com.ludigi.priceflow.frontend.offers;

import com.ludigi.priceflow.frontend.config.feign.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(value = "priceflow-offer-scraper", configuration = {FeignConfiguration.class})
public interface OfferRestClient {
    @GetMapping("/api/offers/mock/prices")
    PriceHistoryResponse findAllMock();
    @PostMapping("/api/offers")
    void createOffer(CreateOfferCommand command);

    record PriceHistoryResponse(List<OfferPrice> prices) {}

    record OfferPrice(Price price, LocalDateTime time) {
    }

    record Price(double value, Currency currency) {
    }

    enum Currency {
        NONE,
        PLN
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
