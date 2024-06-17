package com.ludigi.product.adapter.out;

import com.ludigi.product.Product;

class ProductEntityMapper {
    static ProductEntity from(Product product) {
        ProductEntity entity = new ProductEntity();
        entity.setId(Long.valueOf(product.getId().id()));
        entity.setName(product.getTitle());
        entity.setDescription(entity.getDescription());
        return entity;
    }
}
