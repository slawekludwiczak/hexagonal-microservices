package com.ludigi.priceflow.offer.scraping.policy.deactivation.specification.jsoup;

import com.ludigi.priceflow.offer.common.vo.Selector;
import com.ludigi.priceflow.offer.common.vo.SelectorType;
import com.ludigi.priceflow.offer.scraping.policy.deactivation.specification.ElementExtractor;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import java.util.Optional;

public class JsoupElementExtractor implements ElementExtractor {
    @Override
    public Optional<String> extractElementValue(String html, Selector selector) {
        Elements elements = Jsoup.parse(html).select(selector.selector());
        if (elements.isEmpty()) {
            return Optional.empty();
        } else if (selector.type() == SelectorType.CSS) {
            return Optional.of(elements.text());
        }
        throw new IllegalArgumentException("Unsupported selector type " + selector.type());
    }
}
