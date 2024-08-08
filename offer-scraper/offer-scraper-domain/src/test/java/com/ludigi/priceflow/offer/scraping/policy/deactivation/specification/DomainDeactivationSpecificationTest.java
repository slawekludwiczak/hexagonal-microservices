package com.ludigi.priceflow.offer.scraping.policy.deactivation.specification;

import com.ludigi.priceflow.offer.common.vo.OfferUrl;
import com.ludigi.priceflow.offer.scraping.policy.deactivation.specification.jsoup.JsoupElementExtractor;
import com.ludigi.priceflow.offer.scraping.scraper.HttpStatus;
import com.ludigi.priceflow.offer.scraping.scraper.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DomainDeactivationSpecificationTest {
    private DomainDeactivationSpecification domainDeactivationSpecification;

    @Nested
    class AllegroLokalnie {
        @BeforeEach
        void init() {
            domainDeactivationSpecification = new DomainDeactivationSpecification(
                    new JsoupElementExtractor()
            );
        }

        @Test
        void shouldDeactivateAllegroLokalnieOfferWithBanner() {
            Response response = new Response(
                    new OfferUrl("https://allegrolokalnie.pl/oferta/asdf"),
                    HttpStatus.HTTP_20x,
                    """
                            <html>
                            <body>
                            <div role="alert" class="mlc-offer-ended-banner">
                                <span class="mlc-offer-ended-banner__icon">
                                </span>
                            <p class="ml-text-medium ml-text-semi-bold mlc-offer-ended-banner__text">
                                            Przedmiot został sprzedany lub publikacja ogłoszenia została przez Ciebie zakończona.
                            </p>
                            </div>
                            </body>
                            </html>
                            """);
            boolean result = domainDeactivationSpecification.test(response);
            assertTrue(result);
        }

        @Test
        void shouldNotDeactivateAllegroLokalnieOfferWithoutBanner() {
            Response response = new Response(
                    new OfferUrl("https://allegrolokalnie.pl/oferta/asdf"),
                    HttpStatus.HTTP_20x,
                    """
                            <html>
                            <body>
                            <div class="offer">
                                <p>some offer</p>
                            </div>
                            </body>
                            </html>
                            """);
            boolean result = domainDeactivationSpecification.test(response);
            assertFalse(result);
        }
    }
}