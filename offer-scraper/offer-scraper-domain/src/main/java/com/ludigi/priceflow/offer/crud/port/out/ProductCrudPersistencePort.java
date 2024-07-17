package com.ludigi.priceflow.offer.crud.port.out;

import com.ludigi.priceflow.offer.common.vo.ProductId;
import com.ludigi.priceflow.offer.crud.Product;

import java.util.Optional;

public interface ProductCrudPersistencePort {
    Optional<Product> findById(ProductId productId);
    void save(ProductId product);
}
