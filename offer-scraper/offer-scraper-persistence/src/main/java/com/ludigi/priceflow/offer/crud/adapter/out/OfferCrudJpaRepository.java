package com.ludigi.priceflow.offer.crud.adapter.out;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

interface OfferCrudJpaRepository extends CrudRepository<OfferCrudJpaModel, UUID> {
}
