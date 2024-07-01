package com.ludigi.priceflow.offer.scheduling.adapter.out;

import com.ludigi.priceflow.offer.common.vo.OfferUrl;
import com.ludigi.priceflow.offer.scheduling.ScheduledOffer;
import com.ludigi.priceflow.offer.scheduling.UnscheduledOffer;
import com.ludigi.priceflow.offer.scheduling.port.out.OfferSchedulingPersistencePort;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
class OfferSchedulingRepositoryAdapter implements OfferSchedulingPersistencePort {
    private final OfferSchedulingJpaRepository offerSchedulingJpaRepository;

    public OfferSchedulingRepositoryAdapter(OfferSchedulingJpaRepository offerSchedulingJpaRepository) {
        this.offerSchedulingJpaRepository = offerSchedulingJpaRepository;
    }

    @Override
    public List<UnscheduledOffer> findUnscheduledOffers(int size) {
        return offerSchedulingJpaRepository.findAllUnscheduled(PageRequest.ofSize(size))
                .stream()
                .map(jpaEntity -> new UnscheduledOffer(jpaEntity.getId(), new OfferUrl(jpaEntity.getUrl())))
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
