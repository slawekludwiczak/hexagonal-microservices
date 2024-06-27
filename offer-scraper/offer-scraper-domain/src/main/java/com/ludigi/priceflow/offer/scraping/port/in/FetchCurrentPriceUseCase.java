package com.ludigi.priceflow.offer.scraping.port.in;

import com.ludigi.priceflow.offer.scraping.ActiveOffer;
import com.ludigi.priceflow.offer.scraping.Price;
import com.ludigi.priceflow.offer.scraping.port.out.OfferPersistencePort;

import java.util.Optional;
import java.util.UUID;

public class FetchCurrentPriceUseCase {
    private final OfferPersistencePort offerPersistencePort;

    public FetchCurrentPriceUseCase(OfferPersistencePort offerPersistencePort) {
        this.offerPersistencePort = offerPersistencePort;
    }

    public void fetchCurrentPrice(UUID offerId) {
        ActiveOffer activeOffer = offerPersistencePort.findById(offerId).orElseThrow();
        Optional<Price> price = activeOffer.fetchCurrentPrice();

    }
}
