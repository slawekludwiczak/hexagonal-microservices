package com.ludigi.priceflow.offer.scraping;

import com.ludigi.priceflow.offer.common.vo.Price;

import java.time.LocalDateTime;
import java.util.UUID;

public class PricePoint {
    private UUID offerId;
    private Price price;
    private LocalDateTime time;

    public PricePoint(UUID offerId, Price price, LocalDateTime time) {
        this.offerId = offerId;
        this.price = price;
        this.time = time;
    }

    public UUID getOfferId() {
        return offerId;
    }

    public Price getPrice() {
        return price;
    }

    public LocalDateTime getTime() {
        return time;
    }
}
