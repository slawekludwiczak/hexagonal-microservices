package com.ludigi.priceflow.offer.scraping.scraper;

import com.ludigi.priceflow.offer.common.vo.OfferUrl;

import java.util.Objects;

public record Response(OfferUrl url, HttpStatus httpStatus, String body) {
    public Response {
        Objects.requireNonNull(url);
        Objects.requireNonNull(httpStatus);
        Objects.requireNonNull(body);
    }
}
