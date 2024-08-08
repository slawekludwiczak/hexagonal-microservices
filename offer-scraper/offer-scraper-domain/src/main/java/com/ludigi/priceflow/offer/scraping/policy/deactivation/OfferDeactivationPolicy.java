package com.ludigi.priceflow.offer.scraping.policy.deactivation;

import com.ludigi.priceflow.offer.scraping.ActiveOffer;
import com.ludigi.priceflow.offer.scraping.policy.deactivation.specification.DomainDeactivationSpecification;
import com.ludigi.priceflow.offer.scraping.policy.deactivation.specification.ElementExtractor;
import com.ludigi.priceflow.offer.scraping.policy.deactivation.specification.ElementNotExistDeactivationSpecification;
import com.ludigi.priceflow.offer.scraping.policy.deactivation.specification.HttpStatusDeactivationSpecification;
import com.ludigi.priceflow.offer.scraping.scraper.Response;

import java.util.List;
import java.util.function.Predicate;

public class OfferDeactivationPolicy {
    private final ActiveOffer offer;
    private final Response response;
    private final List<Predicate<Response>> deactivationSpecs;

    public OfferDeactivationPolicy(ActiveOffer offer, Response response, ElementExtractor extractor) {
        this.offer = offer;
        this.response = response;
        deactivationSpecs = List.of(
                new HttpStatusDeactivationSpecification(),
                new DomainDeactivationSpecification(extractor),
                new ElementNotExistDeactivationSpecification(
                        offer.getSelector(),
                        extractor
                )
        );
    }

    public OfferDeactivationPolicy(ActiveOffer offer, Response response, List<Predicate<Response>> deactivationSpecs) {
        this.offer = offer;
        this.response = response;
        this.deactivationSpecs = deactivationSpecs;
    }

    public void apply() {
        for (Predicate<Response> deactivationSpec : deactivationSpecs) {
            if (deactivationSpec.test(response)) {
                offer.deactivate();
            }
        }
    }
}
