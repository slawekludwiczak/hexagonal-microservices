package com.ludigi.priceflow.offer.scraping;

import com.ludigi.priceflow.offer.common.vo.OfferUrl;
import com.ludigi.priceflow.offer.common.vo.PageType;
import com.ludigi.priceflow.offer.common.vo.Price;
import com.ludigi.priceflow.offer.common.vo.Selector;
import com.ludigi.priceflow.offer.scraping.extractor.PriceExtractor;
import com.ludigi.priceflow.offer.scraping.policy.deactivation.OfferDeactivationPolicy;
import com.ludigi.priceflow.offer.scraping.policy.deactivation.specification.jsoup.JsoupElementExtractor;
import com.ludigi.priceflow.offer.scraping.scraper.Response;

import java.util.Optional;
import java.util.UUID;

public class ActiveOffer {
    private UUID id;
    private OfferUrl url;
    private Selector selector;
    private PageType pageType;
    private boolean active;

    public ActiveOffer(UUID id, OfferUrl url, Selector selector, PageType pageType, boolean active) {
        this.id = id;
        this.url = url;
        this.selector = selector;
        this.pageType = pageType;
        this.active = active;
    }

    public Optional<Price> fetchCurrentPrice(PriceExtractor priceExtractor) {
        Response response = pageType.getScraper().fetchHtml(url.url());
        OfferDeactivationPolicy offerDeactivationPolicy = new OfferDeactivationPolicy(
                this,
                response,
                new JsoupElementExtractor()
        );
        offerDeactivationPolicy.apply();
        if (active) {
            return priceExtractor.extractPrice(response.body(), selector);
        } else {
            return Optional.empty();
        }
    }

    public void deactivate() {
        active = false;
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

    public boolean isActive() {
        return active;
    }

    public boolean isInactive() {
        return !active;
    }
}
