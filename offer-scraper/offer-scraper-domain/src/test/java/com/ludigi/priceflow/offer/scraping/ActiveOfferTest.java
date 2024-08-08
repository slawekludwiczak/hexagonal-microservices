package com.ludigi.priceflow.offer.scraping;

import com.ludigi.priceflow.offer.common.vo.*;
import com.ludigi.priceflow.offer.scraping.extractor.PriceExtractor;
import com.ludigi.priceflow.offer.scraping.scraper.HttpStatus;
import com.ludigi.priceflow.offer.scraping.scraper.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class ActiveOfferTest {

    @Test
    void shouldReturnEmptyWhenDeactivated() {
        PageType pageTypeMock = Mockito.mock(PageType.class);
        PriceExtractor priceExtractorMock = Mockito.mock(PriceExtractor.class);
        Mockito.when(pageTypeMock.getScraper())
                .thenReturn(url -> new Response(new OfferUrl(url), HttpStatus.HTTP_20x, ""));
        ActiveOffer offer = new ActiveOffer(
                UUID.randomUUID(),
                new OfferUrl("https://example.com"),
                new Selector("p", SelectorType.CSS),
                pageTypeMock,
                true);
        Optional<Price> price = offer.fetchCurrentPrice(priceExtractorMock);
        assertTrue(price.isEmpty());
        assertTrue(offer.isInactive());
    }
}