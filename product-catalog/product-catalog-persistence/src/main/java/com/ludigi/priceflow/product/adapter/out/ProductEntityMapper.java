package com.ludigi.priceflow.product.adapter.out;

import com.ludigi.priceflow.product.Product;
import com.ludigi.priceflow.product.ProductId;
import com.ludigi.priceflow.product.UserId;

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

    public static Product to(ProductEntity entity) {
        return Product.withId(
                new ProductId(entity.getId().toString()),
                entity.getName(),
                entity.getDescription(),
                new UserId(entity.getAddedBy().toString())
        );
    }
}
