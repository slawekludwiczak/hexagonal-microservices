package com.ludigi.priceflow.offer.scraping.extractor;

import com.ludigi.priceflow.offer.common.vo.OfferUrl;
import com.ludigi.priceflow.offer.common.vo.Selector;

import java.util.List;

public interface OffersUrlExtractor {
    List<OfferUrl> extractOfferUrls(String html, String baseUrl, Selector selector);
}
