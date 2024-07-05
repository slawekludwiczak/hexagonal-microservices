package com.ludigi.priceflow.offer.scraping.extractor;

import com.ludigi.priceflow.offer.common.vo.Price;
import com.ludigi.priceflow.offer.common.vo.Selector;

import java.util.Optional;

public interface PriceExtractor {
    Optional<Price> extractPrice(String html, Selector selector);
}
