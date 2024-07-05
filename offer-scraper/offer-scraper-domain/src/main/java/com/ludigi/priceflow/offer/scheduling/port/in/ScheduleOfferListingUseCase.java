package com.ludigi.priceflow.offer.scheduling.port.in;

import com.ludigi.priceflow.offer.common.events.EventPublisher;

class ScheduleOfferListingUseCase {
    private static final int DEFAULT_POOL_SIZE = 10;
    private final EventPublisher eventPublisher;

    public ScheduleOfferListingUseCase(EventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }
}
