package com.ludigi.priceflow.offer.scraping;

import com.ludigi.priceflow.offer.scraping.scraper.Scraper;
import com.ludigi.priceflow.offer.scraping.scraper.jsoup.JsoupScraper;

public enum PageType {
    HTML(new JsoupScraper());

    private final Scraper scraper;

    PageType(Scraper scraper) {
        this.scraper = scraper;
    }

    public Scraper getScraper() {
        return scraper;
    }
}
