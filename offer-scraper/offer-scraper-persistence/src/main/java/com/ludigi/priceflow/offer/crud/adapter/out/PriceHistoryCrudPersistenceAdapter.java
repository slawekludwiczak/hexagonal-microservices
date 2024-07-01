package com.ludigi.priceflow.offer.crud.adapter.out;

import com.ludigi.priceflow.offer.common.vo.Currency;
import com.ludigi.priceflow.offer.common.vo.Price;
import com.ludigi.priceflow.offer.crud.OfferPrice;
import com.ludigi.priceflow.offer.crud.port.out.PriceHistoryPersistencePort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
class PriceHistoryCrudPersistenceAdapter implements PriceHistoryPersistencePort {
    private final PriceHistoryCrudJpaRepository priceHistoryRepository;

    public PriceHistoryCrudPersistenceAdapter(PriceHistoryCrudJpaRepository priceHistoryRepository) {
        this.priceHistoryRepository = priceHistoryRepository;
    }

    @Override
    public List<OfferPrice> findAllByOfferId(UUID offerId) {
        return priceHistoryRepository.findAllByOfferId(offerId)
                .stream()
                .map(entity -> new OfferPrice(
                        new Price(entity.getPrice(), Currency.valueOf(entity.getCurrency())),
                        entity.getTime()
                )).toList();
    }
}
