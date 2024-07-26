package com.ludigi.priceflow.offer.scraping.adapter.out;

import com.ludigi.priceflow.offer.common.vo.OfferUrl;
import com.ludigi.priceflow.offer.common.vo.PageType;
import com.ludigi.priceflow.offer.common.vo.Selector;
import com.ludigi.priceflow.offer.common.vo.SelectorType;
import com.ludigi.priceflow.offer.scraping.ActiveOffer;
import com.ludigi.priceflow.offer.scraping.InactiveOffer;
import com.ludigi.priceflow.offer.scraping.port.out.OfferPersistencePort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
class OfferPersistenceAdapter implements OfferPersistencePort {
    private final OfferJpaModelRepository offerJpaModelRepository;

    public OfferPersistenceAdapter(OfferJpaModelRepository offerJpaModelRepository) {
        this.offerJpaModelRepository = offerJpaModelRepository;
    }

    @Override
    public Optional<ActiveOffer> findById(UUID offerId) {
        return offerJpaModelRepository.findById(offerId)
                .map(entity -> new ActiveOffer(
                        entity.getId(),
                        new OfferUrl(entity.getUrl()),
                        new Selector(entity.getSelector(), SelectorType.valueOf(entity.getSelectorType())),
                        PageType.valueOf(entity.getPageType())
                ));
    }

    @Override
    @Transactional
    public void save(InactiveOffer offer) {
        offerJpaModelRepository.findById(offer.getId())
                .orElseThrow()
                .setActive(false);
    }
}
