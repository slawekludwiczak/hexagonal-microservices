package com.ludigi.priceflow.offer.extractor.jsoup;

import com.ludigi.priceflow.offer.common.vo.PriceSelector;
import com.ludigi.priceflow.offer.common.vo.SelectorType;
import com.ludigi.priceflow.offer.common.vo.Currency;
import com.ludigi.priceflow.offer.common.vo.Price;
import com.ludigi.priceflow.offer.scraping.extractor.jsoup.JsoupPriceExtractor;
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
                    new PriceSelector("p.price", SelectorType.CSS)
            );
            assertFalse(price.isEmpty());
            Price returnedPrice = price.get();
            assertEquals(expectedPrice.value(), returnedPrice.value());
            assertEquals(expectedPrice.currency(), returnedPrice.currency());
        }
    }

    @Nested
    class InvalidSelector {
        private final String html = """
                    <html>
                        <body>
                            <p class="price">123,45zł</p>
                        </body>
                    </html>
                    """;

        @Test
        void shouldNotFindPriceForInvalidSelector() {
            Optional<Price> price = jsoupPriceExtractor.extractPrice(html, new PriceSelector("p.value", SelectorType.CSS));
            assertTrue(price.isEmpty());
        }
    }

}
