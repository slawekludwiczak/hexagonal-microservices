package com.ludigi.product.port.in;

import com.ludigi.product.ProductId;

import java.util.UUID;

public interface CreateProductPort {
    ProductId createProduct(CreateProductCommand createProductCommand, UUID userId);

    record CreateProductCommand(String name, String description) {}
}