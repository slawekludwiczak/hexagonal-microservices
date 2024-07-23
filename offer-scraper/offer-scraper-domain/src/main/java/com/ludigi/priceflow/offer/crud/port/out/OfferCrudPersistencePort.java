package com.ludigi.priceflow.offer.crud.port.out;

import com.ludigi.priceflow.offer.common.vo.ProductId;
import com.ludigi.priceflow.offer.crud.Offer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OfferCrudPersistencePort {
    void save(Offer offer);
    List<Offer> findAllByProductId(ProductId productId);

    Optional<Offer> findById(UUID offerId);
}
