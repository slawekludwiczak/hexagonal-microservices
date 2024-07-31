package com.ludigi.priceflow.offer.scraping.policy.deactivation;

import com.ludigi.priceflow.offer.scraping.scraper.HttpStatus;
import com.ludigi.priceflow.offer.scraping.scraper.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HttpStatusDeactivationSpecificationTest {
    private final HttpStatusDeactivationSpecification httpStatusSpecification = new HttpStatusDeactivationSpecification();

    @Test
    void shouldFailForStatusCode200x() {
        assertFalse(httpStatusSpecification.test(new Response(HttpStatus.HTTP_20x, "")));
    }
    @Test
    void shouldPassForStatusCodeOtherThan200x() {
        assertTrue(httpStatusSpecification.test(new Response(HttpStatus.HTTP_30x, "")));
        assertTrue(httpStatusSpecification.test(new Response(HttpStatus.HTTP_40x, "")));
        assertTrue(httpStatusSpecification.test(new Response(HttpStatus.HTTP_50x, "")));
    }
}