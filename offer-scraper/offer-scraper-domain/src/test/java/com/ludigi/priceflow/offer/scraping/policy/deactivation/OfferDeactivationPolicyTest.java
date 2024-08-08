package com.ludigi.priceflow.offer.scraping.policy.deactivation;

import com.ludigi.priceflow.offer.scraping.ActiveOffer;
import com.ludigi.priceflow.offer.scraping.scraper.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class OfferDeactivationPolicyTest {
    @Mock
    private ActiveOffer offer;
    @Mock
    private Response response;
    private OfferDeactivationPolicy deactivationPolicy;

    @Test
    void shouldDeactivateOfferForSingleTrueDeactivationSpec() {
        deactivationPolicy = new OfferDeactivationPolicy(offer, response, List.of(
                response -> true
        ));
        deactivationPolicy.apply();
        Mockito.verify(offer, Mockito.only()).deactivate();
    }

    @Test
    void shouldDeactivateOfferForMixedDeactivationSpecStartingWithTrue() {
        deactivationPolicy = new OfferDeactivationPolicy(offer, response, List.of(
                response -> true,
                response1 -> false
        ));
        deactivationPolicy.apply();
        Mockito.verify(offer, Mockito.only()).deactivate();
    }

    @Test
    void shouldDeactivateOfferForMixedDeactivationSpecStartingWithFalse() {
        deactivationPolicy = new OfferDeactivationPolicy(offer, response, List.of(
                response -> false,
                response1 -> true
        ));
        deactivationPolicy.apply();
        Mockito.verify(offer, Mockito.only()).deactivate();
    }

    @Test
    void shouldNotDeactivateOfferForEmptyDeactivationSpec() {
        deactivationPolicy = new OfferDeactivationPolicy(offer, response, Collections.emptyList());
        deactivationPolicy.apply();
        Mockito.verify(offer, Mockito.never()).deactivate();
    }

    @Test
    void shouldNotDeactivateOfferForSingleFalseDeactivationSpec() {
        deactivationPolicy = new OfferDeactivationPolicy(offer, response, List.of(
                response1 -> false
        ));
        deactivationPolicy.apply();
        Mockito.verify(offer, Mockito.never()).deactivate();
    }

}