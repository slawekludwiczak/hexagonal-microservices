package com.ludigi.priceflow.offer.crud;

import com.ludigi.priceflow.offer.common.exception.AlreadyExistsException;
import com.ludigi.priceflow.offer.common.vo.*;

import java.util.List;
import java.util.UUID;

public class Product {
    private ProductId productId;
    private List<OfferUrl> offerUrls;

    public Product(ProductId productId, List<OfferUrl> offerUrls) {
        this.productId = productId;
        this.offerUrls = offerUrls;
    }

    public Offer addOffer(OfferUrl url, Selector selector, PageType pageType, RefreshPeriod refreshPeriod) {
        if (isUnique(url)) {
            return new Offer(
                    UUID.randomUUID(),
                    this.productId,
                    url,
                    selector,
                    pageType,
                    refreshPeriod
            );
        } else {
            throw new AlreadyExistsException("Product %s already has offer %s".formatted(productId.value(), url.url()));
        }
    }

    private boolean isUnique(OfferUrl offerUrl) {
        for (OfferUrl url : offerUrls) {
            if (url.equals(offerUrl)) {
                return false;
            }
        }
        return true;
    }
}
