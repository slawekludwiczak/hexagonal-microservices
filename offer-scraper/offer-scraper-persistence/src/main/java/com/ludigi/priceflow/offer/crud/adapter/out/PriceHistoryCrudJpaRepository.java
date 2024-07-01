package com.ludigi.priceflow.offer.crud.adapter.out;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

interface PriceHistoryCrudJpaRepository extends CrudRepository<PriceHistoryJpaModel, Long> {
    List<PriceHistoryJpaModel> findAllByOfferId(UUID offerId);
}
