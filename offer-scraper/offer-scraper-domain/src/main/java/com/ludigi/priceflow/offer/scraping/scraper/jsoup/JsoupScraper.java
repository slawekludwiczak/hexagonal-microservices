package com.ludigi.priceflow.offer.scraping.scraper.jsoup;

import com.ludigi.priceflow.offer.scraping.scraper.HttpStatus;
import com.ludigi.priceflow.offer.scraping.scraper.Response;
import com.ludigi.priceflow.offer.scraping.scraper.Scraper;
import com.ludigi.priceflow.offer.scraping.scraper.exception.ScraperException;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;

public class JsoupScraper implements Scraper {
    @Override
    public Response fetchHtml(String url) {
        try {
            Connection.Response response = Jsoup.connect(url).execute();
            return new Response(HttpStatus.from(response.statusCode()), response.body());
        } catch (IOException e) {
            throw new ScraperException(e);
        }
    }
}
