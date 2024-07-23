package com.ludigi.priceflow.offer.crud.port.in;

import com.ludigi.priceflow.offer.crud.Offer;
import com.ludigi.priceflow.offer.crud.port.out.OfferCrudPersistencePort;

import java.util.Optional;
import java.util.UUID;

public class FindOfferUseCase {
    private final OfferCrudPersistencePort offerCrudPersistencePort;

    public FindOfferUseCase(OfferCrudPersistencePort offerCrudPersistencePort) {
        this.offerCrudPersistencePort = offerCrudPersistencePort;
    }

    public Optional<Offer> findById(UUID offerId) {
        return offerCrudPersistencePort.findById(offerId);
    }
}
