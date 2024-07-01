package com.ludigi.priceflow.offer.scheduling;

import java.time.LocalDateTime;
import java.util.UUID;

public class ScheduledOffer {
    private final UUID id;
    private final LocalDateTime scheduledAt;

    public ScheduledOffer(UUID id, LocalDateTime scheduledAt) {
        this.id = id;
        this.scheduledAt = scheduledAt;
    }

    public UUID getId() {
        return id;
    }

    public LocalDateTime getScheduledAt() {
        return scheduledAt;
    }
}
