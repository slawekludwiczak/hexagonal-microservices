package com.ludigi.product.adapter.out;

import com.ludigi.product.Product;

import java.util.UUID;

class ProductEntityMapper {
    static ProductEntity from(Product product) {
        ProductEntity entity = new ProductEntity();
        entity.setId(UUID.fromString(product.getId().id()));
        entity.setName(product.getName());
        entity.setDescription(product.getDescription());
        entity.setAddedBy(UUID.fromString(product.getAddedBy().id()));
        return entity;
    }
}
