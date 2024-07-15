package com.ludigi.priceflow.product.port.in;

import com.ludigi.priceflow.product.Product;
import com.ludigi.priceflow.product.ProductId;
import com.ludigi.priceflow.product.events.ProductAddedEvent;
import com.ludigi.priceflow.product.port.out.EventPublisher;
import com.ludigi.priceflow.product.port.out.ProductPersistencePort;

import java.util.List;
import java.util.Optional;

public class ProductCrudService implements ProductCommandPort, ProductQueryPort {
    private final ProductPersistencePort productPersistencePort;
    private final EventPublisher eventPublisher;

    public ProductCrudService(ProductPersistencePort productPersistencePort, EventPublisher eventPublisher) {
        this.productPersistencePort = productPersistencePort;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public ProductId createProduct(CreateProductCommand createProductCommand) {
        Product product = Product.withoutId(
                createProductCommand.name(),
                createProductCommand.description(),
                createProductCommand.userId()
        );
        productPersistencePort.save(product);
        eventPublisher.publish(new ProductAddedEvent(product.getId().id()));
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
