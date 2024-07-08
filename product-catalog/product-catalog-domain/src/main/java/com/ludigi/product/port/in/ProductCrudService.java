package com.ludigi.product.port.in;

import com.ludigi.product.Product;
import com.ludigi.product.ProductId;
import com.ludigi.product.port.out.ProductPersistencePort;

import java.util.List;
import java.util.Optional;

public class ProductCrudService implements ProductCommandPort, ProductQueryPort {
    private final ProductPersistencePort productPersistencePort;

    public ProductCrudService(ProductPersistencePort productPersistencePort) {
        this.productPersistencePort = productPersistencePort;
    }

    @Override
    public ProductId createProduct(CreateProductCommand createProductCommand) {
        Product product = Product.withoutId(
                createProductCommand.name(),
                createProductCommand.description(),
                createProductCommand.userId()
        );
        productPersistencePort.save(product);
        return product.getId();
    }

    @Override
    public Optional<Product> findById(String id) {
        return productPersistencePort.findById(id);
    }

    @Override
    public List<Product> findAllProducts() {
        return productPersistencePort.findAll();
    }
}
