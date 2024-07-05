package com.ludigi.priceflow.offer.scraping.port.in;

import com.ludigi.priceflow.offer.scraping.port.out.OfferPersistencePort;

class ScrapeOffersUseCase {
    private final OfferPersistencePort offerPersistencePort;

    public ScrapeOffersUseCase(OfferPersistencePort offerPersistencePort) {
        this.offerPersistencePort = offerPersistencePort;
    }

    void scrapeOffers(String seedUrl) {

    }
}
