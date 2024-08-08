package com.ludigi.priceflow.offer.scraping.policy.deactivation.specification;

import com.ludigi.priceflow.offer.common.vo.Selector;
import com.ludigi.priceflow.offer.scraping.scraper.Response;

import java.util.Optional;
import java.util.function.Predicate;

public class ElementNotExistDeactivationSpecification implements Predicate<Response> {
    private final Selector selector;
    private final ElementExtractor elementExtractor;

    public ElementNotExistDeactivationSpecification(Selector selector,
                                                    ElementExtractor elementExtractor) {
        this.selector = selector;
        this.elementExtractor = elementExtractor;
    }

    @Override
    public boolean test(Response response) {
        Optional<String> elementValue = elementExtractor.extractElementValue(response.body(), selector);
        return elementValue.isEmpty();
    }
}
