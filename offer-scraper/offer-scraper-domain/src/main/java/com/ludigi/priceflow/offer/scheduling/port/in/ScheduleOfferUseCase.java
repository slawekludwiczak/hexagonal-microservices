package com.ludigi.priceflow.offer.scheduling.port.in;

import com.ludigi.priceflow.offer.common.events.EventPublisher;
import com.ludigi.priceflow.offer.common.events.OfferScheduledEvent;
import com.ludigi.priceflow.offer.scheduling.UnscheduledOffer;
import com.ludigi.priceflow.offer.scheduling.port.out.OfferSchedulingPersistencePort;

import java.util.List;

public class ScheduleOfferUseCase {
    public static final int DEFAULT_POOL_SIZE = 10;
    private final OfferSchedulingPersistencePort offerSchedulingPersistencePort;
    private final EventPublisher eventPublisher;

    public ScheduleOfferUseCase(OfferSchedulingPersistencePort offerSchedulingPersistencePort, EventPublisher eventPublisher) {
        this.offerSchedulingPersistencePort = offerSchedulingPersistencePort;
        this.eventPublisher = eventPublisher;
    }

    public void scheduleOffers() {
        List<UnscheduledOffer> unscheduledOffers = offerSchedulingPersistencePort.findUnscheduledOffers(DEFAULT_POOL_SIZE);
        unscheduledOffers.stream()
                .map(UnscheduledOffer::schedule)
                .forEach(scheduledOffer -> {
                    offerSchedulingPersistencePort.save(scheduledOffer);
                    eventPublisher.publish(
                            new OfferScheduledEvent(scheduledOffer.getId(), scheduledOffer.getUrl().url())
                    );
                });
    }

}
