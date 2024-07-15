package com.ludigi.priceflow.offer.crud.adapter.out.product;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "products")
class ProductJpaModel {
    @Id
    private UUID id;

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
}
