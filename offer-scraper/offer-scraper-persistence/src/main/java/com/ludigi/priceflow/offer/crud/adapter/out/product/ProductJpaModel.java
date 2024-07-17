package com.ludigi.priceflow.offer.crud.adapter.out.product;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "products")
class ProductJpaModel {
    @Id
    private UUID id;
    @OneToMany
    @JoinColumn(name = "product_id")
    private List<ProductOfferJpaModel> offers = new ArrayList<>();

    public ProductJpaModel() {
    }

    public ProductJpaModel(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<ProductOfferJpaModel> getOffers() {
        return offers;
    }

    public void setOffers(List<ProductOfferJpaModel> offers) {
        this.offers = offers;
    }
}
