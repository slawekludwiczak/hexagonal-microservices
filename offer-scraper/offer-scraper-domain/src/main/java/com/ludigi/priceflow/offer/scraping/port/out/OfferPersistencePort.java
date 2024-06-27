package com.ludigi.priceflow.offer.scraping.port.out;

import com.ludigi.priceflow.offer.scraping.ActiveOffer;

import java.util.Optional;
import java.util.UUID;

public interface OfferPersistencePort {
    Optional<ActiveOffer> findById(UUID offerId);
}
