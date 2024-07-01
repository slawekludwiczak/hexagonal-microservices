package com.ludigi.priceflow.offer.scraping.adapter.out;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

interface PricePointJpaModelRepository extends CrudRepository<PricePointJpaModel, UUID> {
}
