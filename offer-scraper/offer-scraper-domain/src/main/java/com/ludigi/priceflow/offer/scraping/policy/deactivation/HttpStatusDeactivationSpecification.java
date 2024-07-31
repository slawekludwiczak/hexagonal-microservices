package com.ludigi.priceflow.offer.scraping.policy.deactivation;

import com.ludigi.priceflow.offer.scraping.scraper.HttpStatus;
import com.ludigi.priceflow.offer.scraping.scraper.Response;

import java.util.function.Predicate;

public class HttpStatusDeactivationSpecification implements Predicate<Response> {

    @Override
    public boolean test(Response response) {
        return response.httpStatus() != HttpStatus.HTTP_20x;
    }
}
