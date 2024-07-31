package com.ludigi.priceflow.offer.scraping.port.in;

import com.ludigi.priceflow.offer.common.vo.Price;
import com.ludigi.priceflow.offer.scraping.ActiveOffer;
import com.ludigi.priceflow.offer.scraping.PricePoint;
import com.ludigi.priceflow.offer.scraping.extractor.PriceExtractor;
import com.ludigi.priceflow.offer.scraping.extractor.jsoup.JsoupPriceExtractor;
import com.ludigi.priceflow.offer.scraping.port.out.OfferPersistencePort;
import com.ludigi.priceflow.offer.scraping.port.out.PricePointPersistencePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public class FetchCurrentPriceUseCase {
    private final static Logger LOG = LoggerFactory.getLogger(FetchCurrentPriceUseCase.class);
    private final OfferPersistencePort offerPersistencePort;
    private final PricePointPersistencePort pricePointPersistencePort;
    private PriceExtractor priceExtractor = new JsoupPriceExtractor();

    public FetchCurrentPriceUseCase(OfferPersistencePort offerPersistencePort, PricePointPersistencePort pricePointPersistencePort) {
        this.offerPersistencePort = offerPersistencePort;
        this.pricePointPersistencePort = pricePointPersistencePort;
    }

    public FetchCurrentPriceUseCase(OfferPersistencePort offerPersistencePort,
                                    PricePointPersistencePort pricePointPersistencePort,
                                    PriceExtractor priceExtractor) {
        this(offerPersistencePort, pricePointPersistencePort);
        this.priceExtractor = priceExtractor;
    }

    public void fetchCurrentPrice(UUID offerId) {
        ActiveOffer activeOffer = offerPersistencePort.findById(offerId).orElseThrow();
        Optional<Price> price = activeOffer.fetchCurrentPrice(priceExtractor);
        if (activeOffer.isInactive()) {
            offerPersistencePort.save(activeOffer);
            LOG.debug("Offer {} with url {} was deactivated", activeOffer.getId(), activeOffer.getUrl().url());
        } else {
            pricePointPersistencePort.save(price
                    .map(p -> new PricePoint(
                            activeOffer.getId(),
                            p,
                            LocalDateTime.now())
                    ).get()
            );
            LOG.debug("Fetched price {} for offer {} with url {}", price, activeOffer.getId(), activeOffer.getUrl().url());
        }
    }
}
