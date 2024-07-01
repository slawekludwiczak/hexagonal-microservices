package com.ludigi.priceflow.offer.scheduling.port.out;

import com.ludigi.priceflow.offer.scheduling.ScheduledOffer;
import com.ludigi.priceflow.offer.scheduling.UnscheduledOffer;

import java.util.List;

public interface OfferSchedulingPersistencePort {
    List<UnscheduledOffer> findUnscheduledOffers(int size);
    void save(ScheduledOffer scheduledOffer);
}
