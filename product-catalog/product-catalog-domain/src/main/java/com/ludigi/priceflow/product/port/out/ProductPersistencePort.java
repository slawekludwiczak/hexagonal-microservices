package com.ludigi.priceflow.product.port.out;

import com.ludigi.priceflow.product.Product;

import java.util.List;
import java.util.Optional;

public interface ProductPersistencePort {
    void save(Product product);

    Optional<Product> findById(String id);

    List<Product> findAll();
}
