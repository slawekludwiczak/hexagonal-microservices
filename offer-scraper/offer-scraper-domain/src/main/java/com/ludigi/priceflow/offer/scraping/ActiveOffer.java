package com.ludigi.priceflow.offer.scraping;

import com.ludigi.priceflow.offer.common.vo.OfferUrl;
import com.ludigi.priceflow.offer.common.vo.PageType;
import com.ludigi.priceflow.offer.common.vo.Price;
import com.ludigi.priceflow.offer.common.vo.Selector;
import com.ludigi.priceflow.offer.scraping.extractor.PriceExtractor;

import java.util.Optional;
import java.util.UUID;

public class ActiveOffer {
    private UUID id;
    private OfferUrl url;
    private Selector selector;
    private PageType pageType;

    public ActiveOffer(UUID id, OfferUrl url, Selector selector, PageType pageType) {
        this.id = id;
        this.url = url;
        this.selector = selector;
        this.pageType = pageType;
    }

    public Optional<Price> fetchCurrentPrice(PriceExtractor priceExtractor) {
        String htmlSource = pageType.getScraper().fetchHtml(url.url());
        return priceExtractor.extractPrice(htmlSource, selector);
    }

    public UUID getId() {
        return id;
    }

    public OfferUrl getUrl() {
        return url;
    }

    public Selector getSelector() {
        return selector;
    }

    public PageType getPageType() {
        return pageType;
    }

    public InactiveOffer deactivate() {
        return new InactiveOffer(this.id);
    }
}
