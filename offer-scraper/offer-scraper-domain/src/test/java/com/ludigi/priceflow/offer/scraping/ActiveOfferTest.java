package com.ludigi.priceflow.offer.scraping;

import com.ludigi.priceflow.offer.common.vo.*;
import com.ludigi.priceflow.offer.scraping.extractor.PriceExtractor;
import com.ludigi.priceflow.offer.scraping.scraper.HttpStatus;
import com.ludigi.priceflow.offer.scraping.scraper.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class ActiveOfferTest {

    @ParameterizedTest
    @EnumSource(value = HttpStatus.class, names = {"HTTP_30x", "HTTP_40x", "HTTP_50x"})
    void shouldDeactivateForStatusCodeOtherThan20x(HttpStatus status) {
        PageType pageTypeMock = Mockito.mock(PageType.class);
        PriceExtractor priceExtractorMock = Mockito.mock(PriceExtractor.class);
        Mockito.when(pageTypeMock.getScraper())
                .thenReturn(url -> new Response(status, ""));
        ActiveOffer offer = new ActiveOffer(
                UUID.randomUUID(),
                new OfferUrl("https://example.com"),
                new Selector("", SelectorType.CSS),
                pageTypeMock,
                true);
        Optional<Price> price = offer.fetchCurrentPrice(priceExtractorMock);
        assertTrue(offer.isInactive());
        assertTrue(price.isEmpty());
    }

    @Test
    void shouldDeativateWhenStatusCode20xAndNoPrice() {
        PageType pageTypeMock = Mockito.mock(PageType.class);
        PriceExtractor priceExtractorMock = Mockito.mock(PriceExtractor.class);
        Mockito.when(pageTypeMock.getScraper())
                .thenReturn(url -> new Response(HttpStatus.HTTP_20x, ""));
        Mockito.when(priceExtractorMock.extractPrice(any(), any()))
                .thenReturn(Optional.empty());
        ActiveOffer offer = new ActiveOffer(
                UUID.randomUUID(),
                new OfferUrl("https://example.com"),
                new Selector("", SelectorType.CSS),
                pageTypeMock,
                true);
        Optional<Price> price = offer.fetchCurrentPrice(priceExtractorMock);
        assertTrue(price.isEmpty());
        assertTrue(offer.isInactive());
    }
}