package com.ludigi.priceflow.offer.crud;

import com.ludigi.priceflow.offer.common.vo.*;

import java.util.UUID;

public class Offer {
    private UUID id;
    private ProductId productId;
    private OfferUrl url;
    private PriceSelector selector;
    private PageType pageType;
    private RefreshPeriod refreshPeriod;

    public Offer(UUID id,
                 ProductId productId,
                 OfferUrl url,
                 PriceSelector selector,
                 PageType pageType,
                 RefreshPeriod refreshPeriod) {
        this.id = id;
        this.productId = productId;
        this.url = url;
        this.selector = selector;
        this.pageType = pageType;
        this.refreshPeriod = refreshPeriod;
    }

    public UUID getId() {
        return id;
    }

    public ProductId getProductId() {
        return productId;
    }

    public OfferUrl getUrl() {
        return url;
    }

    public PriceSelector getSelector() {
        return selector;
    }

    public PageType getPageType() {
        return pageType;
    }

    public RefreshPeriod getRefreshPeriod() {
        return refreshPeriod;
    }
}
