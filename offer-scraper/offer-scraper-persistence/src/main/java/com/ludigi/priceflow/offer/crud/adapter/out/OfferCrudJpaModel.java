package com.ludigi.priceflow.offer.crud.adapter.out;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Duration;
import java.util.UUID;

@Entity
@Table(name = "offers")
class OfferCrudJpaModel {
    @Id
    private UUID id;
    @Column(name = "product_id")
    private UUID productId;
    private String url;
    private String selector;
    private String selectorType;
    private String pageType;
    private Duration refreshInterval;

    public OfferCrudJpaModel() {
    }

    public OfferCrudJpaModel(UUID id, UUID productId, String url, String selector, String selectorType, String pageType, Duration refreshInterval) {
        this.id = id;
        this.productId = productId;
        this.url = url;
        this.selector = selector;
        this.selectorType = selectorType;
        this.pageType = pageType;
        this.refreshInterval = refreshInterval;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSelector() {
        return selector;
    }

    public void setSelector(String selector) {
        this.selector = selector;
    }

    public String getSelectorType() {
        return selectorType;
    }

    public void setSelectorType(String selectorType) {
        this.selectorType = selectorType;
    }

    public String getPageType() {
        return pageType;
    }

    public void setPageType(String pageType) {
        this.pageType = pageType;
    }

    public Duration getRefreshInterval() {
        return refreshInterval;
    }

    public void setRefreshInterval(Duration refreshInterfal) {
        this.refreshInterval = refreshInterfal;
    }
}
