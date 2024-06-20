package com.ludigi.product.port.in;

import com.ludigi.product.Product;
import com.ludigi.product.ProductId;
import com.ludigi.product.port.out.ProductPersistPort;

public class ProductCrudService implements ProductCommandPort {
    private final ProductPersistPort productPersistPort;

    public ProductCrudService(ProductPersistPort productPersistPort) {
        this.productPersistPort = productPersistPort;
    }

    public ProductId createProduct(CreateProductCommand createProductCommand) {
        Product product = Product.withoutId(
                createProductCommand.name(),
                createProductCommand.description(),
                createProductCommand.userId()
        );
        productPersistPort.save(product);
        return product.getId();
    }
}