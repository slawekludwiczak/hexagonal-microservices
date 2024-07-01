package com.ludigi.priceflow.offer.scheduling.port.in;

import com.ludigi.priceflow.offer.scheduling.UnscheduledOffer;
import com.ludigi.priceflow.offer.scheduling.port.out.OfferSchedulingPersistencePort;

import java.util.List;

public class ScheduleOfferUseCase {
    private static final int DEFAULT_POOL_SIZE = 10;
    private final OfferSchedulingPersistencePort offerSchedulingRepository;

    public ScheduleOfferUseCase(OfferSchedulingPersistencePort scheduledOfferRepository) {
        this.offerSchedulingRepository = scheduledOfferRepository;
    }

    public void scheduleOffers() {
        List<UnscheduledOffer> unscheduledOffers = offerSchedulingRepository.findUnscheduledOffers(DEFAULT_POOL_SIZE);
        unscheduledOffers.stream()
                .map(UnscheduledOffer::schedule)
                .forEach(offerSchedulingRepository::save);
    }
}
