package com.ludigi.priceflow.offer.scraping.port.in;

import com.ludigi.priceflow.offer.common.vo.*;
import com.ludigi.priceflow.offer.scraping.ActiveOffer;
import com.ludigi.priceflow.offer.scraping.extractor.PriceExtractor;
import com.ludigi.priceflow.offer.scraping.port.out.OfferPersistencePort;
import com.ludigi.priceflow.offer.scraping.port.out.PricePointPersistencePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class FetchCurrentPriceUseCaseTest {
    @InjectMocks
    private FetchCurrentPriceUseCase useCase;
    @Mock
    private OfferPersistencePort offerPersistencePort;
    @Mock
    private PricePointPersistencePort pricePointPersistencePort;
    @Mock
    private PriceExtractor priceExtractor;

    @Test
    void shouldThrowNoSuchElementExceptionWhenOfferNotExists() {
        UUID offerId = UUID.randomUUID();
        Mockito.when(offerPersistencePort.findById(offerId)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> useCase.fetchCurrentPrice(offerId));
    }

    @Test
    void shouldPersistPriceWhenPriceIsFetched() {
        UUID offerId = UUID.randomUUID();
        Mockito.when(offerPersistencePort.findById(offerId))
                .thenReturn(
                        Optional.of(new ActiveOffer(
                                        offerId,
                                        new OfferUrl("http://example.com"),
                                        new Selector("p.price", SelectorType.CSS),
                                        PageType.HTML
                                )
                        )
                );
        Mockito.when(priceExtractor.extractPrice(any(), any()))
                .thenReturn(Optional.of(new Price(123.45, Currency.PLN)));
        useCase.fetchCurrentPrice(offerId);
        Mockito.verify(pricePointPersistencePort, Mockito.times(1)).save(any());
    }
}