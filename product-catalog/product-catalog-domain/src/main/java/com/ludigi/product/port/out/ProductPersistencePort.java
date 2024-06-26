package com.ludigi.product.port.out;

import com.ludigi.product.Product;

import java.util.Optional;

public interface ProductPersistencePort {
    void save(Product product);

    Optional<Product> findById(String id);
}
