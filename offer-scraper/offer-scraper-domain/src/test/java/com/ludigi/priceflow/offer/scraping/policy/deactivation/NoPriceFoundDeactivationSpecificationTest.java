package com.ludigi.priceflow.offer.scraping.policy.deactivation;

import com.ludigi.priceflow.offer.common.vo.Currency;
import com.ludigi.priceflow.offer.common.vo.Price;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class NoPriceFoundDeactivationSpecificationTest {
    private final NoPriceFoundDeactivationSpecification noPriceFoundDeactivationSpecification =
            new NoPriceFoundDeactivationSpecification();

    @Test
    void shouldDeactivateOnEmptyPrice() {
        assertTrue(noPriceFoundDeactivationSpecification.test(Optional.empty()));
    }

    @Test
    void shouldNotDeactivateOnExistingPrice() {
        assertFalse(noPriceFoundDeactivationSpecification.test(Optional.of(new Price(5.5, Currency.PLN))));
    }
}