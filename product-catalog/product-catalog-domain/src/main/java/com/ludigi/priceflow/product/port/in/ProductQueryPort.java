package com.ludigi.priceflow.product.port.in;

import com.ludigi.priceflow.product.Product;

import java.util.List;
import java.util.Optional;

public interface ProductQueryPort {
    Optional<Product> findById(String id);
    List<Product> findAllProducts();
}
