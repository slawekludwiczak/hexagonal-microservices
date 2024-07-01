package com.ludigi.priceflow.offer.scraping;

import com.ludigi.priceflow.offer.common.vo.OfferUrl;
import com.ludigi.priceflow.offer.common.vo.PageType;
import com.ludigi.priceflow.offer.common.vo.Price;
import com.ludigi.priceflow.offer.common.vo.PriceSelector;
import com.ludigi.priceflow.offer.scraping.extractor.PriceExtractor;
import com.ludigi.priceflow.offer.scraping.extractor.jsoup.JsoupPriceExtractor;

import java.util.Optional;
import java.util.UUID;

public class ActiveOffer {
    private UUID id;
    private OfferUrl url;
    private PriceSelector selector;
    private PageType pageType;

    public ActiveOffer(UUID id, OfferUrl url, PriceSelector selector, PageType pageType) {
        this.id = id;
        this.url = url;
        this.selector = selector;
        this.pageType = pageType;
    }

    public Optional<Price> fetchCurrentPrice() {
        String htmlSource = pageType.getScraper().fetchHtml(url.url());
        PriceExtractor priceExtractor = new JsoupPriceExtractor();
        return priceExtractor.extractPrice(htmlSource, selector);
    }

    public UUID getId() {
        return id;
    }

    public OfferUrl getUrl() {
        return url;
    }

    public PriceSelector getSelector() {
        return selector;
    }

    public PageType getPageType() {
        return pageType;
    }
}
