package com.ludigi.priceflow.offer.scraping.port.in;

import com.ludigi.priceflow.offer.scraping.ActiveOffer;
import com.ludigi.priceflow.offer.common.vo.Price;
import com.ludigi.priceflow.offer.scraping.PricePoint;
import com.ludigi.priceflow.offer.scraping.port.out.OfferPersistencePort;
import com.ludigi.priceflow.offer.scraping.port.out.PricePointPersistencePort;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public class FetchCurrentPriceUseCase {
    private final OfferPersistencePort offerPersistencePort;
    private final PricePointPersistencePort pricePointPersistencePort;

    public FetchCurrentPriceUseCase(OfferPersistencePort offerPersistencePort, PricePointPersistencePort pricePointPersistencePort) {
        this.offerPersistencePort = offerPersistencePort;
        this.pricePointPersistencePort = pricePointPersistencePort;
    }

    public void fetchCurrentPrice(UUID offerId) {
        ActiveOffer activeOffer = offerPersistencePort.findById(offerId).orElseThrow();
        Optional<Price> price = activeOffer.fetchCurrentPrice();
        price.map(p -> new PricePoint(activeOffer.getId(), p, LocalDateTime.now())).ifPresentOrElse(
                pricePointPersistencePort::save,
                () -> System.out.printf("Price for offer %s not found\n", activeOffer.getId())

        );
        System.out.printf("Fetched price %s for offer %s%n", price, activeOffer.getUrl().url());
    }
}
