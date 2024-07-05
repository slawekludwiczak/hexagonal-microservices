package com.ludigi.priceflow.offer.scraping.extractor.jsoup;

import com.ludigi.priceflow.offer.common.vo.OfferUrl;
import com.ludigi.priceflow.offer.common.vo.Selector;
import com.ludigi.priceflow.offer.common.vo.SelectorType;
import com.ludigi.priceflow.offer.scraping.scraper.jsoup.JsoupScraper;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsoupOffersUrlExtractorTest {

    @Nested
    class OlxTest {
        JsoupOffersUrlExtractor jsoupOffersUrlExtractor = new JsoupOffersUrlExtractor();

        @Test
        void shouldScrapeUrlsFromOlx() {
            String url = "https://www.olx.pl/nieruchomosci/mieszkania/sprzedaz/";
            String baseUrl = "https://www.olx.pl";
            JsoupScraper jsoupScraper = new JsoupScraper();
            String html = jsoupScraper.fetchHtml(url);
            List<OfferUrl> offerUrls = jsoupOffersUrlExtractor.extractOfferUrls(html, baseUrl, new Selector("a:has(h6)", SelectorType.CSS));
            offerUrls.forEach(System.out::println);
        }
    }

    @Nested
    class OtoDomTest {
        JsoupOffersUrlExtractor jsoupOffersUrlExtractor = new JsoupOffersUrlExtractor();

        @Test
        void shouldScrapeUrlsFromOlx() {
            String url = "https://www.otodom.pl/pl/wyniki/sprzedaz/mieszkanie/cala-polska";
            String baseUrl = "https://www.otodom.pl";
            JsoupScraper jsoupScraper = new JsoupScraper();
            String html = jsoupScraper.fetchHtml(url);
            List<OfferUrl> offerUrls = jsoupOffersUrlExtractor.extractOfferUrls(html, baseUrl, new Selector("a[data-cy=listing-item-link]", SelectorType.CSS));
            offerUrls.forEach(System.out::println);
        }
    }
}
