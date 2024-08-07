package com.ludigi.priceflow.offer.scraping.extractor.jsoup;

import com.ludigi.priceflow.offer.common.vo.OfferUrl;
import com.ludigi.priceflow.offer.common.vo.Selector;
import com.ludigi.priceflow.offer.common.vo.SelectorType;
import com.ludigi.priceflow.offer.scraping.scraper.Response;
import com.ludigi.priceflow.offer.scraping.scraper.jsoup.JsoupScraper;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

class JsoupOffersUrlExtractorTest {
    private final JsoupOffersUrlExtractor jsoupOffersUrlExtractor = new JsoupOffersUrlExtractor();

    @Nested
    class OlxTest {
        @Test
        void shouldScrapeUrlsFromOlx() {
            String url = "https://www.olx.pl/nieruchomosci/mieszkania/sprzedaz/";
            String baseUrl = "https://www.olx.pl";
            JsoupScraper jsoupScraper = new JsoupScraper();
            Response response = jsoupScraper.fetchHtml(url);
            List<OfferUrl> offerUrls = jsoupOffersUrlExtractor.extractOfferUrls(response.body(), baseUrl, new Selector("a:has(h6)", SelectorType.CSS));
            offerUrls.forEach(System.out::println);
        }
    }

    @Nested
    class OtoDomTest {
        @Test
        void shouldScrapeUrlsFromOlx() {
            String url = "https://www.otodom.pl/pl/wyniki/sprzedaz/mieszkanie/cala-polska";
            String baseUrl = "https://www.otodom.pl";
            JsoupScraper jsoupScraper = new JsoupScraper();
            Response response = jsoupScraper.fetchHtml(url);
            List<OfferUrl> offerUrls = jsoupOffersUrlExtractor.extractOfferUrls(response.body(), baseUrl, new Selector("a[data-cy=listing-item-link]", SelectorType.CSS));
            offerUrls.forEach(System.out::println);
        }
    }
}
