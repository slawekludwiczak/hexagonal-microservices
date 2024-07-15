package com.ludigi.priceflow.offer.crud.adapter.out.product;

import com.ludigi.priceflow.offer.common.vo.ProductId;
import com.ludigi.priceflow.offer.crud.port.out.ProductCrudPersistencePort;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
class ProductCrudPersistenceAdapter implements ProductCrudPersistencePort {
    private final ProductJpaRepository repository;

    public ProductCrudPersistenceAdapter(ProductJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(ProductId product) {
        repository.save(new ProductJpaModel(UUID.fromString(product.value())));
    }
}
