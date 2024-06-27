package com.ludigi.product.adapter.out;

import com.ludigi.product.Product;
import com.ludigi.product.ProductId;
import com.ludigi.product.port.out.ProductPersistencePort;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryProductPersistenceAdapter implements ProductPersistencePort {
    private final Map<ProductId, Product> products = new HashMap<>();

    @Override
    public void save(Product product) {
        products.put(product.getId(), product);
    }

    @Override
    public Optional<Product> findById(String id) {
        return Optional.ofNullable(products.get(new ProductId(id)));
    }
}