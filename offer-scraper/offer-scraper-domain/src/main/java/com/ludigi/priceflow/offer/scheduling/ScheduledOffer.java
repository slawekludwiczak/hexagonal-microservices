package com.ludigi.priceflow.offer.scheduling;

import com.ludigi.priceflow.offer.common.vo.OfferUrl;

import java.time.LocalDateTime;
import java.util.UUID;

public class ScheduledOffer {
    private final UUID id;
    private final OfferUrl url;
    private final LocalDateTime scheduledAt;

    public ScheduledOffer(UUID id, OfferUrl url, LocalDateTime scheduledAt) {
        this.id = id;
        this.url = url;
        this.scheduledAt = scheduledAt;
    }

    public UUID getId() {
        return id;
    }

    public OfferUrl getUrl() {
        return url;
    }

    public LocalDateTime getScheduledAt() {
        return scheduledAt;
    }
}
