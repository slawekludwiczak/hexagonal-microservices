package com.ludigi.priceflow.offer.scraping;

import com.ludigi.priceflow.offer.common.vo.OfferUrl;
import com.ludigi.priceflow.offer.common.vo.PageType;

import java.util.List;

class OfferListing {
    private String url;
    private PageType pageType;

    List<OfferUrl> fetchOffers(String url) {
//        String html = pageType.getScraper().fetchHtml(url);
//        OffersUrlExtractor offersUrlExtractor = new JsoupOffersUrlExtractor();
        return List.of();
    }
}
