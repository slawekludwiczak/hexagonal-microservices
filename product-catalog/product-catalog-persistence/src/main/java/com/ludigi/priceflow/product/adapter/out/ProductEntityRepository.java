package com.ludigi.priceflow.product.adapter.out;

import org.springframework.data.repository.ListCrudRepository;

import java.util.UUID;

public interface ProductEntityRepository extends ListCrudRepository<ProductEntity, UUID> {
}
