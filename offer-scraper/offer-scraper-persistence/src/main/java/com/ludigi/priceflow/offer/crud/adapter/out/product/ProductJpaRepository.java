package com.ludigi.priceflow.offer.crud.adapter.out.product;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

interface ProductJpaRepository extends CrudRepository<ProductJpaModel, UUID> {
}
