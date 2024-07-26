package com.ludigi.priceflow.offer.scheduling.port.in;

import com.ludigi.priceflow.offer.common.events.EventPublisher;
import com.ludigi.priceflow.offer.common.events.OfferScheduledEvent;
import com.ludigi.priceflow.offer.common.vo.OfferUrl;
import com.ludigi.priceflow.offer.scheduling.UnscheduledOffer;
import com.ludigi.priceflow.offer.scheduling.port.out.OfferSchedulingPersistencePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ScheduleOfferUseCaseTest {
    @InjectMocks
    private ScheduleOfferUseCase useCase;
    @Mock
    private OfferSchedulingPersistencePort offerSchedulingPersistencePort;
    @Mock
    private EventPublisher eventPublisher;
    @Captor
    private ArgumentCaptor<OfferScheduledEvent> eventCaptor;

    @Test
    void shoudPersist2OffersAndPublish2EventsFor2UnscheduledOffers() {
        List<UnscheduledOffer> unscheduledOffers = List.of(
                new UnscheduledOffer(UUID.randomUUID(), new OfferUrl("http://example1.com")),
                new UnscheduledOffer(UUID.randomUUID(), new OfferUrl("http://example2.com"))
        );
        Mockito.when(offerSchedulingPersistencePort.findUnscheduledOffers(ScheduleOfferUseCase.DEFAULT_POOL_SIZE))
                .thenReturn(unscheduledOffers);
        useCase.scheduleOffers();
        Mockito.verify(offerSchedulingPersistencePort, Mockito.times(2)).save(Mockito.any());
        Mockito.verify(eventPublisher, Mockito.times(2)).publish(eventCaptor.capture());
        List<OfferScheduledEvent> publishedEvents = eventCaptor.getAllValues();
        assertEquals(2, publishedEvents.size());
    }

    @Test
    void shouldPersistAnyAndPublishNoEventsWhenNoUnscheduledOffers() {
        useCase.scheduleOffers();
        Mockito.verifyNoInteractions(eventPublisher);
        Mockito.verify(offerSchedulingPersistencePort, Mockito.never()).save(Mockito.any());
    }
}