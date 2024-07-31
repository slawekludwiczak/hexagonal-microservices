package com.ludigi.priceflow.offer.scraping.port.in;

import com.ludigi.priceflow.offer.common.vo.Currency;
import com.ludigi.priceflow.offer.common.vo.OfferUrl;
import com.ludigi.priceflow.offer.common.vo.Price;
import com.ludigi.priceflow.offer.scraping.ActiveOffer;
import com.ludigi.priceflow.offer.scraping.PricePoint;
import com.ludigi.priceflow.offer.scraping.extractor.PriceExtractor;
import com.ludigi.priceflow.offer.scraping.port.out.OfferPersistencePort;
import com.ludigi.priceflow.offer.scraping.port.out.PricePointPersistencePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
    void shouldPersistPriceWhenIsFetched() {
        ActiveOffer offer = Mockito.mock(ActiveOffer.class);
        UUID offerId = UUID.randomUUID();
        Mockito.when(offer.getId()).thenReturn(offerId);
        Mockito.when(offer.getUrl()).thenReturn(new OfferUrl("https://example.com"));
        Mockito.when(offerPersistencePort.findById(any()))
                .thenReturn(Optional.of(offer));
        Price fetchedPrice = new Price(5.5, Currency.PLN);
        Mockito.when(offer.fetchCurrentPrice(priceExtractor))
                .thenReturn(Optional.of(fetchedPrice));
        useCase.fetchCurrentPrice(offerId);

        ArgumentCaptor<PricePoint> pricePointArgumentCaptor = ArgumentCaptor.forClass(PricePoint.class);
        Mockito.verify(pricePointPersistencePort, Mockito.times(1)).save(pricePointArgumentCaptor.capture());
        PricePoint persistedPricePoint = pricePointArgumentCaptor.getValue();
        assertEquals(offerId, persistedPricePoint.getOfferId());
        assertEquals(fetchedPrice, persistedPricePoint.getPrice());
    }

    @Test
    void shouldPersistInactiveOfferWhenNoPrice() {
        UUID offerId = UUID.randomUUID();
        ActiveOffer offerMock = Mockito.mock(ActiveOffer.class);
        Mockito.when(offerMock.getId()).thenReturn(offerId);
        Mockito.when(offerMock.getUrl()).thenReturn(new OfferUrl("https://example.com"));
        Mockito.when(offerPersistencePort.findById(offerId))
                .thenReturn(Optional.of(offerMock));
        Mockito.when(offerMock.fetchCurrentPrice(any())).thenReturn(Optional.empty());
        Mockito.when(offerMock.isInactive()).thenReturn(true);
        useCase.fetchCurrentPrice(offerId);
        Mockito.verify(offerPersistencePort, Mockito.times(1)).save(offerMock);
    }
}