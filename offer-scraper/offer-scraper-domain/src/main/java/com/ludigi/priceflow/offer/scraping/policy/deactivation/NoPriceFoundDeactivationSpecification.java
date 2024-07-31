package com.ludigi.priceflow.offer.scraping.policy.deactivation;

import com.ludigi.priceflow.offer.common.vo.Price;

import java.util.Optional;
import java.util.function.Predicate;

public class NoPriceFoundDeactivationSpecification implements Predicate<Optional<Price>> {

    @Override
    public boolean test(Optional<Price> price) {
        return price.isEmpty();
    }
}
