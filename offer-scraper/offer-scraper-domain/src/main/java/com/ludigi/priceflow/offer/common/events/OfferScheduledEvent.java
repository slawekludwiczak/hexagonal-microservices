package com.ludigi.priceflow.offer.common.events;

import java.util.UUID;

public record OfferScheduledEvent(UUID id, String url) {
}
