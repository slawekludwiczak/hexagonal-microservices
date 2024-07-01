package com.ludigi.priceflow.offer.scraping.adapter.out;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "offers")
class OfferJpaModel {
    @Id
    private UUID id;
    private String url;
    private String selector;
    private String selectorType;
    private String pageType;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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
}
