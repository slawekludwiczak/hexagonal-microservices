package com.ludigi.priceflow.offer.scraping.policy.deactivation.specification;

import com.ludigi.priceflow.offer.scraping.scraper.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class ElementExistDeactivationSpecificationTest {
    @Mock
    private ElementExtractor extractor;
    @InjectMocks
    private ElementExistDeactivationSpecification spec;
    @Mock
    private Response response;

    @Test
    void shouldPassWhenElementExist() {
        Mockito.when(response.body()).thenReturn("<html>...</html>");
        Mockito.when(extractor.extractElementValue(any(), any()))
                .thenReturn(Optional.of("existing element value"));
        boolean result = spec.test(response);
        assertTrue(result);
    }

    @Test
    void shouldNotPassWhenElementExist() {
        Mockito.when(response.body()).thenReturn("<html>...</html>");
        Mockito.when(extractor.extractElementValue(any(), any()))
                .thenReturn(Optional.empty());
        boolean result = spec.test(response);
        assertFalse(result);
    }
}