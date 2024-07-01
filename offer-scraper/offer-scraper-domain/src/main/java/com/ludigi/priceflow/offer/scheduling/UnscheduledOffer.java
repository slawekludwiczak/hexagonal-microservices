package com.ludigi.priceflow.offer.scheduling;

import java.time.LocalDateTime;
import java.util.UUID;

public class UnscheduledOffer {
    private final UUID id;

    public UnscheduledOffer(UUID id) {
        this.id = id;
    }

    public ScheduledOffer schedule() {
        return new ScheduledOffer(id, LocalDateTime.now());
    }

    @Override
    public String toString() {
        return "UnscheduledOffer{" +
                "id=" + id +
                '}';
    }
}
