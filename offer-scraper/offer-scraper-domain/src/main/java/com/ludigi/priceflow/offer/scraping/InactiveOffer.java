package com.ludigi.priceflow.offer.scraping;

import java.util.UUID;

public class InactiveOffer {
    private UUID id;

    public InactiveOffer(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
