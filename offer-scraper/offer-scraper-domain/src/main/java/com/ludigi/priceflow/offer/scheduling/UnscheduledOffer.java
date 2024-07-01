package com.ludigi.priceflow.offer.scheduling;

import com.ludigi.priceflow.offer.common.vo.OfferUrl;

import java.time.LocalDateTime;
import java.util.UUID;

public class UnscheduledOffer {
    private final UUID id;
    private final OfferUrl url;

    public UnscheduledOffer(UUID id, OfferUrl url) {
        this.id = id;
        this.url = url;
    }

    public ScheduledOffer schedule() {
        return new ScheduledOffer(id, url, LocalDateTime.now());
    }

    @Override
    public String toString() {
        return "UnscheduledOffer{" +
                "id=" + id +
                '}';
    }
}
