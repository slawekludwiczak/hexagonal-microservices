package com.ludigi.priceflow.offer.crud.port.in;

import com.ludigi.priceflow.offer.common.vo.Price;
import com.ludigi.priceflow.offer.crud.OfferPrice;
import com.ludigi.priceflow.offer.crud.port.out.PriceHistoryPersistencePort;

import java.util.List;
import java.util.UUID;

public class GetPriceHistoryUseCase {
    private final PriceHistoryPersistencePort priceHistoryPersistencePort;

    public GetPriceHistoryUseCase(PriceHistoryPersistencePort priceHistoryPersistencePort) {
        this.priceHistoryPersistencePort = priceHistoryPersistencePort;
    }

    public List<OfferPrice> findPriceHistory(UUID offerId) {
        return priceHistoryPersistencePort.findAllByOfferId(offerId);
    }
}
