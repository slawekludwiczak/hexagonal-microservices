package com.ludigi.priceflow.offer.scraping.scraper;

public interface Scraper {
    Response fetchHtml(String url);
}
