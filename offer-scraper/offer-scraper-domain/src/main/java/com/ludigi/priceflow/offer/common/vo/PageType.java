package com.ludigi.priceflow.offer.common.vo;

import com.ludigi.priceflow.offer.scraping.scraper.Scraper;
import com.ludigi.priceflow.offer.scraping.scraper.jsoup.JsoupScraper;

public enum PageType {
    HTML(new JsoupScraper());
    //TODO SPA

    private final Scraper scraper;

    PageType(Scraper scraper) {
        this.scraper = scraper;
    }

    public Scraper getScraper() {
        return scraper;
    }
}
