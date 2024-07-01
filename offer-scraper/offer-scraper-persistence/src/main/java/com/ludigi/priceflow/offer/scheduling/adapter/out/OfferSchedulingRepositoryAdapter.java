package com.ludigi.priceflow.offer.scheduling.adapter.out;

import com.ludigi.priceflow.offer.scheduling.ScheduledOffer;
import com.ludigi.priceflow.offer.scheduling.UnscheduledOffer;
import com.ludigi.priceflow.offer.scheduling.adapter.out.model.OfferSchedulingJpaModel;
import com.ludigi.priceflow.offer.scheduling.port.out.OfferSchedulingPersistencePort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OfferSchedulingRepositoryAdapter implements OfferSchedulingPersistencePort {
    private final OfferSchedulingJpaRepository offerSchedulingJpaRepository;

    public OfferSchedulingRepositoryAdapter(OfferSchedulingJpaRepository offerSchedulingJpaRepository) {
        this.offerSchedulingJpaRepository = offerSchedulingJpaRepository;
    }

    @Override
    public List<UnscheduledOffer> findUnscheduledOffers(int size) {
        return offerSchedulingJpaRepository.findAllByDurationLongerThan(Duration.ofMinutes(1))
                .stream()
                .map(OfferSchedulingJpaModel::getId)
                .map(UnscheduledOffer::new)
                .toList();
    }

    @Override
    @Transactional
    public void save(ScheduledOffer scheduledOffer) {
        offerSchedulingJpaRepository
                .findById(scheduledOffer.getId())
                .ifPresent(offer -> offer.setScheduledAt(LocalDateTime.now()));
    }
}
