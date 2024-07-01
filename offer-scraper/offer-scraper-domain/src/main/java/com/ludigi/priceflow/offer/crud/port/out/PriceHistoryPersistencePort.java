package com.ludigi.priceflow.offer.crud.port.out;

import com.ludigi.priceflow.offer.crud.OfferPrice;

import java.util.List;
import java.util.UUID;

public interface PriceHistoryPersistencePort {
    List<OfferPrice> findAllByOfferId(UUID offerId);
}
