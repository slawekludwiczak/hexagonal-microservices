package com.ludigi.priceflow.offer.scraping.policy.deactivation.specification;

import com.ludigi.priceflow.offer.common.vo.Selector;
import com.ludigi.priceflow.offer.common.vo.SelectorType;
import com.ludigi.priceflow.offer.scraping.scraper.Response;

import java.util.Map;
import java.util.function.Predicate;

public class DomainDeactivationSpecification implements Predicate<Response> {
    private final Map<String, Predicate<Response>> domainRules;

    public DomainDeactivationSpecification(ElementExtractor extractor) {
        this.domainRules = Map.of("allegrolokalnie.pl", new ElementExistDeactivationSpecification(
                new Selector(".mlc-offer-ended-banner", SelectorType.CSS),
                extractor
        ));
    }

    @Override
    public boolean test(Response response) {
        String domainName = response.url().url().replaceAll("http(s)?://|www\\.|/.*", "");
        Predicate<Response> domainPredicate = domainRules.get(domainName);
        if (domainPredicate == null) {
            return false;
        } else {
            return domainPredicate.test(response);
        }
    }

}
