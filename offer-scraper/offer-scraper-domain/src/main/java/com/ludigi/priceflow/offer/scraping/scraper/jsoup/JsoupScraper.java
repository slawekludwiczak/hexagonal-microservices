package com.ludigi.priceflow.offer.scraping.scraper.jsoup;

import com.ludigi.priceflow.offer.scraping.scraper.Scraper;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.io.UncheckedIOException;

public class JsoupScraper implements Scraper {
    @Override
    public String fetchHtml(String url) {
        try {
            return Jsoup.connect(url).get().html();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
