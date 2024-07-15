package com.ludigi.priceflow.product.port.in;

import com.ludigi.priceflow.product.ProductId;

public interface ProductCommandPort {
    ProductId createProduct(CreateProductCommand createProductCommand);

    record CreateProductCommand(String name, String description, String userId) {}
}
