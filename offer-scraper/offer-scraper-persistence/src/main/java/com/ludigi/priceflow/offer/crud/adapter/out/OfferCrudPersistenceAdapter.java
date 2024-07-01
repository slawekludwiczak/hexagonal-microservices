package com.ludigi.priceflow.offer.crud.adapter.out;

import com.ludigi.priceflow.offer.crud.Offer;
import com.ludigi.priceflow.offer.crud.port.out.OfferCrudPersistencePort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
class OfferCrudPersistenceAdapter implements OfferCrudPersistencePort {
    private final OfferCrudJpaRepository offerCrudJpaRepository;

    public OfferCrudPersistenceAdapter(OfferCrudJpaRepository offerCrudJpaRepository) {
        this.offerCrudJpaRepository = offerCrudJpaRepository;
    }

    @Override
    public void save(Offer offer) {
        OfferCrudJpaModel offerCrudJpaModel = new OfferCrudJpaModel(
                offer.getId(),
                UUID.fromString(offer.getProductId().value()),
                offer.getUrl().url(),
                offer.getSelector().selector(),
                offer.getSelector().type().name(),
                offer.getPageType().name(),
                offer.getRefreshPeriod().asDuration()
        );
        offerCrudJpaRepository.save(offerCrudJpaModel);
    }
}
