package com.ludigi.priceflow.offer.scraping.extractor;

import com.ludigi.priceflow.offer.common.vo.Price;
import com.ludigi.priceflow.offer.common.vo.PriceSelector;

import java.util.Optional;

public interface PriceExtractor {
    Optional<Price> extractPrice(String html, PriceSelector selector);
}
