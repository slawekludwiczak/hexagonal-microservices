package com.ludigi;

import com.ludigi.priceflow.offer.common.vo.Price;
import com.ludigi.priceflow.offer.common.vo.PriceSelector;
import com.ludigi.priceflow.offer.common.vo.SelectorType;
import com.ludigi.priceflow.offer.scraping.extractor.jsoup.JsoupPriceExtractor;
import com.ludigi.priceflow.offer.scraping.scraper.jsoup.JsoupScraper;

import java.text.ParseException;
import java.time.Duration;
import java.util.Optional;

public class Main {
    public static void main(String[] args) throws ParseException {
//        JsoupScraper jsoupScraper = new JsoupScraper();
//        String html = jsoupScraper.fetchHtml("https://www.otodom.pl/pl/oferta/tarnogaj-i-4-pokoje-i-tranwaj-i-park-ID4rhc8");
//        System.out.println(html);
//        JsoupPriceExtractor extractor = new JsoupPriceExtractor();
//        Optional<Price> price = extractor.extractPrice(html, new PriceSelector("strong[aria-label=Cena]", SelectorType.CSS));
//        price.ifPresentOrElse(
//                System.out::println,
//                () -> System.out.println("brak ceny")
//        );
    }
}