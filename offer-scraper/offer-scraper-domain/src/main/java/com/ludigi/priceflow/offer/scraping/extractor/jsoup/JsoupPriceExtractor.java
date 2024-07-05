package com.ludigi.priceflow.offer.scraping.extractor.jsoup;

import com.ludigi.priceflow.offer.common.vo.Selector;
import com.ludigi.priceflow.offer.common.vo.SelectorType;
import com.ludigi.priceflow.offer.common.vo.Currency;
import com.ludigi.priceflow.offer.common.vo.Price;
import com.ludigi.priceflow.offer.scraping.extractor.PriceExtractor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Optional;

public class JsoupPriceExtractor implements PriceExtractor {
    @Override
    public Optional<Price> extractPrice(String html, Selector selector) {
        if (!supports(selector)) {
            throw new IllegalArgumentException("Selector type %s is unsupported".formatted(selector.type()));
        }
        Document document = Jsoup.parse(html);
        Elements elements = document.select(selector.selector());

        return elements.stream()
                .findFirst()
                .map(e -> parseDouble(e.text()))
                .map(price -> new Price(price, Currency.NONE));
    }

    private boolean supports(Selector selector) {
        return selector.type() == SelectorType.CSS;
    }

    private double parseDouble(String number) {
        String numberDotSeparator = number.replaceAll(",", ".").replaceAll("\\s+", "");
        DecimalFormat df = new DecimalFormat("#.#");
        try {
            return df.parse(numberDotSeparator).doubleValue();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
