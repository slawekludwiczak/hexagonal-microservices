package com.ludigi.priceflow.offer.scraping.policy.deactivation.specification;

import com.ludigi.priceflow.offer.common.vo.Selector;

import java.util.Optional;

public interface ElementExtractor {
    Optional<String> extractElementValue(String html, Selector selector);
}