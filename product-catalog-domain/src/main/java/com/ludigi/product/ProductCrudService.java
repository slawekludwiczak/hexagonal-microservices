package com.ludigi.product;

import com.ludigi.product.port.in.CreateProductPort;
import com.ludigi.product.port.out.ProductPersistPort;

import java.util.UUID;

public class ProductCrudService implements CreateProductPort {
    private final ProductPersistPort productPersistPort;

    public ProductCrudService(ProductPersistPort productPersistPort) {
        this.productPersistPort = productPersistPort;
    }

    public ProductId createProduct(CreateProductCommand createProductCommand, UUID userId) {
        Product product = Product.withoutId(
                createProductCommand.name(),
                createProductCommand.description(),
                userId
        );
        productPersistPort.save(product);
        return product.getId();
    }
}