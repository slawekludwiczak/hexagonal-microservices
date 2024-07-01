package com.ludigi.priceflow.offer.crud.port.out;

import com.ludigi.priceflow.offer.crud.Offer;

public interface OfferCrudPersistencePort {
    void save(Offer offer);
}
