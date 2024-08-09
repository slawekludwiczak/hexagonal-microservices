package com.ludigi.priceflow.frontend.rest.client;

import com.ludigi.priceflow.frontend.config.feign.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

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

    @PatchMapping("/api/offers/{id}")
    void updateOffer(@PathVariable("id") UUID offerId,
                     @RequestBody EditOfferCommand editOfferCommand);

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

    record OfferPrice(Price price, LocalDateTime time) { }

    record Price(double value, Currency currency) { }

    enum Currency {
        NONE,
        PLN
    }

    record OffersResponse(List<OfferResponse> offers) {
        public OffersResponse {
            Collections.sort(offers);
        }
    }

    record OfferResponse(UUID id,
                         String offerUrl,
                         String productId,
                         String selector,
                         String selectorType,
                         String pageType,
                         int refreshValue,
                         String refreshUnit,
                         boolean active) implements Comparable<OfferResponse> {
        @Override
        public int compareTo(OfferResponse o) {
            return - Boolean.compare(this.active, o.active);
        }
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

    record EditOfferCommand(String selector,
                            String selectorType,
                            String pageType,
                            int refreshValue,
                            String refreshUnit) { }
}
