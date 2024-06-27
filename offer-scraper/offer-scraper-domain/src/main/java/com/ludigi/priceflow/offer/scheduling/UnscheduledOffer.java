package com.ludigi.priceflow.offer.scheduling;

import java.util.UUID;

public class UnscheduledOffer {
    private UUID id;

    public UnscheduledOffer(UUID id) {
        this.id = id;
    }

    ScheduledOffer schedule() {
        return new ScheduledOffer(id);
    }
}
