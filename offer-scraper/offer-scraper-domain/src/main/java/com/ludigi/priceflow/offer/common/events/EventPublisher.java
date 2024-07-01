package com.ludigi.priceflow.offer.common.events;

public interface EventPublisher {
    void publish(OfferScheduledEvent event);
}
