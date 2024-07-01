package com.ludigi.priceflow.offer.scheduling.adapter.out;

import com.ludigi.priceflow.offer.scheduling.adapter.out.model.OfferSchedulingJpaModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

public interface OfferSchedulingJpaRepository extends JpaRepository<OfferSchedulingJpaModel, UUID> {

    @Query("select o from OfferSchedulingJpaModel o where o.scheduledAt is null")
    List<OfferSchedulingJpaModel> findAllUnscheduled(Pageable page);

    @Query("select o from OfferSchedulingJpaModel o where current_timestamp - o.scheduledAt > ?1")
    List<OfferSchedulingJpaModel> findAllByDurationLongerThan(Duration duration);

}
