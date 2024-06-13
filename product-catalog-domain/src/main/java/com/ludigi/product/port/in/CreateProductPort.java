package com.ludigi.product.port.in;

import com.ludigi.product.ProductId;

public interface CreateProductPort {
    ProductId createProduct(CreateProductCommand createProductCommand);

    record CreateProductCommand(String title, String description) {}
}