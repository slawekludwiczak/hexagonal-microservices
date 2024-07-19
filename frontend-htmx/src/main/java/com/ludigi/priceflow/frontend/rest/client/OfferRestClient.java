package com.ludigi.priceflow.frontend.rest.client;

import com.ludigi.priceflow.frontend.config.feign.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static java.util.stream.Collectors.averagingDouble;
import static java.util.stream.Collectors.groupingBy;

@FeignClient(value = "priceflow-offer-scraper", configuration = {FeignConfiguration.class})
public interface OfferRestClient {
    @GetMapping("/api/offers/{id}/prices")
    PriceHistoryResponse findPriceHistoryByOfferId(@PathVariable("id") String id);

    @PostMapping("/api/offers")
    void createOffer(CreateOfferCommand command);

    @GetMapping("/api/offers")
    OffersResponse findOffersByProductId(@RequestParam("productId") String productId);

    @GetMapping("/api/offers/{id}")
    Optional<OfferResponse> findOfferById(@PathVariable("id") String id);

    record PriceHistoryResponse(List<OfferPrice> prices) {
        public Map<LocalDateTime, Double> getChart() {
            return prices.stream()
                    .collect(groupingBy(
                                    OfferPrice::time,
                                    averagingDouble(op -> op.price().value())
                            )
                    );
        }
    }

    record OfferPrice(Price price, LocalDateTime time) {
    }

    record Price(double value, Currency currency) {
    }

    enum Currency {
        NONE,
        PLN
    }

    record OffersResponse(List<OfferResponse> offers) {
    }

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
    ) {
    }
}
