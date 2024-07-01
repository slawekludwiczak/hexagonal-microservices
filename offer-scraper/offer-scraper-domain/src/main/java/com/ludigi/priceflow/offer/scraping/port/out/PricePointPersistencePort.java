package com.ludigi.priceflow.offer.scraping.port.out;

import com.ludigi.priceflow.offer.scraping.PricePoint;

public interface PricePointPersistencePort {
    void save(PricePoint pricePoint);
}
