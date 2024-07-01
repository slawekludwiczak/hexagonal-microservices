package com.ludigi.priceflow.offer.crud.adapter.out;

import com.ludigi.priceflow.offer.crud.adapter.out.model.OfferCrudJpaModel;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface OfferCrudJpaRepository extends CrudRepository<OfferCrudJpaModel, UUID> {
}
