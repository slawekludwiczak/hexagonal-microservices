package com.ludigi.priceflow.offer.extractor.jsoup;

import com.ludigi.priceflow.offer.common.vo.Selector;
import com.ludigi.priceflow.offer.common.vo.SelectorType;
import com.ludigi.priceflow.offer.common.vo.Currency;
import com.ludigi.priceflow.offer.common.vo.Price;
import com.ludigi.priceflow.offer.scraping.extractor.jsoup.JsoupPriceExtractor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class JsoupPriceExtractorTest {
    private final JsoupPriceExtractor jsoupPriceExtractor = new JsoupPriceExtractor();

    @Nested
    class WithoutCurrency {
        @Test
        void shouldExtractPriceWithDotSeparator() {
            verifyPrice("15.5", new Price(15.5, Currency.NONE));
            verifyPrice("15.54", new Price(15.54, Currency.NONE));
        }

        @Test
        void shouldExtractPriceWithCommaSeparator() {
            verifyPrice("15,5", new Price(15.5, Currency.NONE));
            verifyPrice("15,54", new Price(15.54, Currency.NONE));
        }

        @Test
        void shouldExtractPriceWithSpaceSeparator() {
            verifyPrice("15 5", new Price(155, Currency.NONE));
            verifyPrice("15 54", new Price(1554, Currency.NONE));
        }

        @Test
        void shouldExtractIntegerPrice() {
            verifyPrice("15", new Price(15, Currency.NONE));
        }
    }

    @Nested
    class WithCurrency {
        @Test
        void shouldExtractPriceWithCurrencyAttached() {
            verifyPrice("15.5zł", new Price(15.5, Currency.NONE));
            verifyPrice("15.54zł", new Price(15.54, Currency.NONE));
        }

        @Test
        void shouldExtractPriceWithCurrencySeparatedBySpace() {
            verifyPrice("15.5 zł", new Price(15.5, Currency.NONE));
            verifyPrice("15.54 zł", new Price(15.54, Currency.NONE));
        }
    }

    private void verifyPrice(String htmlPrice, Price expectedPrice) {
        String html = """
                    <html>
                        <body>
                            <p class="price">%s</p>
                        </body>
                    </html>
                    """;
        String htmlWithPrice = html.formatted(htmlPrice);
        Optional<Price> price = jsoupPriceExtractor.extractPrice(
                htmlWithPrice,
                new Selector("p.price", SelectorType.CSS)
        );
        assertFalse(price.isEmpty());
        Price returnedPrice = price.get();
        assertEquals(expectedPrice.value(), returnedPrice.value());
        assertEquals(expectedPrice.currency(), returnedPrice.currency());
    }

    @Nested
    class Selectors {
        private final String html = """
                    <html>
                        <body>
                            <p class="price">123,45zł</p>
                        </body>
                    </html>
                    """;

        @Test
        @DisplayName("Invalid selector")
        void shouldNotFindPriceForInvalidSelector() {
            Optional<Price> price = jsoupPriceExtractor.extractPrice(html, new Selector("p.value", SelectorType.CSS));
            assertTrue(price.isEmpty());
        }

        @Test
        @DisplayName("Valid selector")
        void shouldFindPriceForValidSelector() {
            Optional<Price> price = jsoupPriceExtractor.extractPrice(html, new Selector(".price", SelectorType.CSS));
            assertTrue(price.isPresent());
        }
    }

}
