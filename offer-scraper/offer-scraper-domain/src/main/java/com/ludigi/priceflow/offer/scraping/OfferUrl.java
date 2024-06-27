package com.ludigi.priceflow.offer.scraping;

import java.net.URI;

public record OfferUrl(String url) {
    public OfferUrl {
        URI.create(url); //throw if url is invalid
    }
}
