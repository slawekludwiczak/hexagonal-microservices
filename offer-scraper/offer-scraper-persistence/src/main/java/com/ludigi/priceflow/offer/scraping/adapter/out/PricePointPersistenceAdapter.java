package com.ludigi.priceflow.offer.scraping.adapter.out;

import com.ludigi.priceflow.offer.scraping.PricePoint;
import com.ludigi.priceflow.offer.scraping.port.out.PricePointPersistencePort;
import org.springframework.stereotype.Component;

@Component
class PricePointPersistenceAdapter implements PricePointPersistencePort {
    private final PricePointJpaModelRepository pricePointJpaModelRepository;

    public PricePointPersistenceAdapter(PricePointJpaModelRepository pricePointJpaModelRepository) {
        this.pricePointJpaModelRepository = pricePointJpaModelRepository;
    }

    @Override
    public void save(PricePoint pricePoint) {
        PricePointJpaModel entity = new PricePointJpaModel();
        entity.setPrice(pricePoint.getPrice().value());
        entity.setCurrency(pricePoint.getPrice().currency().name());
        entity.setTime(pricePoint.getTime());
        entity.setOfferId(pricePoint.getOfferId());
        pricePointJpaModelRepository.save(entity);
    }
}
