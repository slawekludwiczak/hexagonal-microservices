package com.ludigi.priceflow.offer.scraping;

import com.ludigi.priceflow.offer.scraping.extractor.PriceExtractor;
import com.ludigi.priceflow.offer.scraping.extractor.jsoup.JsoupPriceExtractor;

import java.util.Optional;
import java.util.UUID;

public class ActiveOffer {
    private UUID id;
    private OfferUrl url;
    private PriceSelector selector;
    private PageType pageType;

    public Optional<Price> fetchCurrentPrice() {
        String htmlSource = pageType.getScraper().fetchHtml(url.url());
        PriceExtractor priceExtractor = new JsoupPriceExtractor();
        return priceExtractor.extractPrice(htmlSource, selector);
    }
}
