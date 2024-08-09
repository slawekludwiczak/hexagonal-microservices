package com.ludigi.priceflow.offer.crud;

import com.ludigi.priceflow.offer.common.vo.*;

import java.util.UUID;

public class Offer {
    private UUID id;
    private ProductId productId;
    private OfferUrl url;
    private Selector selector;
    private PageType pageType;
    private RefreshPeriod refreshPeriod;
    private boolean active;

    public Offer(UUID id,
                 ProductId productId,
                 OfferUrl url,
                 Selector selector,
                 PageType pageType,
                 RefreshPeriod refreshPeriod,
                 boolean active) {
        this.id = id;
        this.productId = productId;
        this.url = url;
        this.selector = selector;
        this.pageType = pageType;
        this.refreshPeriod = refreshPeriod;
        this.active = active;
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

    public Selector getSelector() {
        return selector;
    }

    public void setSelector(Selector selector) {
        this.selector = selector;
    }

    public PageType getPageType() {
        return pageType;
    }

    public void setPageType(PageType pageType) {
        this.pageType = pageType;
    }

    public RefreshPeriod getRefreshPeriod() {
        return refreshPeriod;
    }

    public void setRefreshPeriod(RefreshPeriod refreshPeriod) {
        this.refreshPeriod = refreshPeriod;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setProductId(ProductId productId) {
        this.productId = productId;
    }

    public void setUrl(OfferUrl url) {
        this.url = url;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
