package com.ludigi.product.port.out;

import com.ludigi.product.Product;
import com.ludigi.product.ProductId;

import java.util.Optional;

public interface ProductQueryPort {
    Optional<Product> findById(ProductId productId);
}
