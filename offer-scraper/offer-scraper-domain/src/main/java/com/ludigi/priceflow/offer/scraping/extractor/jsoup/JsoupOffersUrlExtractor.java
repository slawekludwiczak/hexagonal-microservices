package com.ludigi.priceflow.offer.scraping.extractor.jsoup;

import com.ludigi.priceflow.offer.common.vo.OfferUrl;
import com.ludigi.priceflow.offer.common.vo.Selector;
import com.ludigi.priceflow.offer.common.vo.SelectorType;
import com.ludigi.priceflow.offer.scraping.extractor.OffersUrlExtractor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class JsoupOffersUrlExtractor implements OffersUrlExtractor {

    @Override
    public List<OfferUrl> extractOfferUrls(String html, String baseUrl, Selector selector) {
        if (!supports(selector)) {
            throw new IllegalArgumentException("Selector type %s is unsupported".formatted(selector.type()));
        }
        Document document = Jsoup.parse(html);
        Elements elements = document.select(selector.selector());
        List<OfferUrl> offers = new ArrayList<>();
        for (Element element : elements) {
            Elements a = element.select("a[href]");
            String href = a.attr("href");
            String url = normalizeUrl(href, baseUrl);
            offers.add(new OfferUrl(url));
        }
        return offers;
    }

    private boolean supports(Selector selector) {
        return selector.type() == SelectorType.CSS;
    }

    private String normalizeUrl(String href, String baseUrl) {
        if (href.startsWith("http")) {
            return href;
        } else {
            return baseUrl + "/" + href;
        }
    }
}
