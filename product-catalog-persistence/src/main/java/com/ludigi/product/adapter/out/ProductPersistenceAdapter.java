package com.ludigi.product.adapter.out;

import com.ludigi.product.Product;
import com.ludigi.product.ProductId;
import com.ludigi.product.port.out.ProductPersistPort;

import java.util.HashMap;
import java.util.Map;

public class ProductPersistenceAdapter implements ProductPersistPort {
    private final Map<ProductId, Product> products = new HashMap<>();

    @Override
    public void save(Product product) {
        products.put(product.getId(), product);
    }
}
