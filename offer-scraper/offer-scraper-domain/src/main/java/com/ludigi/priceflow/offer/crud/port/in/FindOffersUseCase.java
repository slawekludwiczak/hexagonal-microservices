package com.ludigi.priceflow.offer.crud.port.in;

import com.ludigi.priceflow.offer.common.vo.ProductId;
import com.ludigi.priceflow.offer.crud.Offer;
import com.ludigi.priceflow.offer.crud.port.out.OfferCrudPersistencePort;

import java.util.List;

public class FindOffersUseCase {
    private final OfferCrudPersistencePort offerCrudPersistencePort;

    public FindOffersUseCase(OfferCrudPersistencePort offerCrudPersistencePort) {
        this.offerCrudPersistencePort = offerCrudPersistencePort;
    }

    public List<Offer> findAllByProductId(String productId) {
        return offerCrudPersistencePort.findAllByProductId(new ProductId(productId));
    }
}
