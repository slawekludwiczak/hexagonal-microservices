package com.ludigi.priceflow.offer.scraping.scraper;

import java.util.Objects;

public record Response(HttpStatus httpStatus, String body) {
    public Response {
        Objects.requireNonNull(httpStatus);
        Objects.requireNonNull(body);
    }
}
