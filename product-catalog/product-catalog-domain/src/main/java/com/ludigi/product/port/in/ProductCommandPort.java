package com.ludigi.product.port.in;

import com.ludigi.product.ProductId;

public interface ProductCommandPort {
    ProductId createProduct(CreateProductCommand createProductCommand);

    record CreateProductCommand(String name, String description, String userId) {}
}