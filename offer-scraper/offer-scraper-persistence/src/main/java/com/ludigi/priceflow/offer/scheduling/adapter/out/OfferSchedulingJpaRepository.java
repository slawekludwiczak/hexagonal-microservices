package com.ludigi.priceflow.offer.scheduling.adapter.out;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

interface OfferSchedulingJpaRepository extends JpaRepository<OfferSchedulingJpaModel, UUID> {

    @Query("""
            select 
                o from OfferSchedulingJpaModel o 
            where 
                o.active = true
                and 
                (o.scheduledAt is null or current_timestamp - o.scheduledAt > o.refreshInterval)
            """)
    List<OfferSchedulingJpaModel> findAllUnscheduled(Pageable page);

}
