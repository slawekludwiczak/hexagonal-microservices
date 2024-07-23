package com.ludigi.priceflow.offer.crud.adapter.out;

import com.ludigi.priceflow.offer.common.vo.*;
import com.ludigi.priceflow.offer.crud.Offer;
import com.ludigi.priceflow.offer.crud.port.out.OfferCrudPersistencePort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
                offer.getRefreshPeriod().asDuration(),
                offer.getRefreshPeriod().unit()
        );
        offerCrudJpaRepository.save(offerCrudJpaModel);
    }

    @Override
    public List<Offer> findAllByProductId(ProductId productId) {
        return offerCrudJpaRepository
                .findAllByProductId(UUID.fromString(productId.value()))
                .stream()
                .map(OfferCrudPersistenceAdapter::fromEntity)
                .toList();
    }

    @Override
    public Optional<Offer> findById(UUID offerId) {
        return offerCrudJpaRepository.findById(offerId).map(OfferCrudPersistenceAdapter::fromEntity);
    }

    private static Offer fromEntity(OfferCrudJpaModel entity) {
        return new Offer(
                entity.getId(),
                new ProductId(entity.getProductId().toString()),
                new OfferUrl(entity.getUrl()),
                new Selector(entity.getSelector(), SelectorType.valueOf(entity.getSelectorType())),
                PageType.valueOf(entity.getPageType()),
                RefreshPeriod.from(entity.getRefreshInterval(), entity.getRefreshUnit())
        );
    }
}
