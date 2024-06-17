package com.ludigi.product;

import com.ludigi.product.port.in.CreateProductPort;
import com.ludigi.product.port.out.ProductPersistPort;

public class ProductCrudService implements CreateProductPort {
    private final ProductPersistPort productPersistPort;

    public ProductCrudService(ProductPersistPort productPersistPort) {
        this.productPersistPort = productPersistPort;
    }

    public ProductId createProduct(CreateProductCommand createProductCommand) {
        Product product = Product.withoutId(createProductCommand.name(), createProductCommand.description());
        productPersistPort.save(product);
        return product.getId();
    }
}