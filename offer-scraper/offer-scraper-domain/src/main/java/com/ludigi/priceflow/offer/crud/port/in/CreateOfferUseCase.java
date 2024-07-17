package com.ludigi.priceflow.offer.crud.port.in;

import com.ludigi.priceflow.offer.common.vo.*;
import com.ludigi.priceflow.offer.crud.Offer;
import com.ludigi.priceflow.offer.crud.Product;
import com.ludigi.priceflow.offer.crud.port.out.OfferCrudPersistencePort;
import com.ludigi.priceflow.offer.crud.port.out.ProductCrudPersistencePort;

import java.time.temporal.ChronoUnit;
import java.util.UUID;

public class CreateOfferUseCase {
    private final ProductCrudPersistencePort productCrudPersistencePort;
    private final OfferCrudPersistencePort offerCrudPersistencePort;

    public CreateOfferUseCase(ProductCrudPersistencePort productCrudPersistencePort, OfferCrudPersistencePort offerCrudPersistencePort) {
        this.productCrudPersistencePort = productCrudPersistencePort;
        this.offerCrudPersistencePort = offerCrudPersistencePort;
    }

    public UUID createOffer(CreateOfferCommand command) {
        Product product = productCrudPersistencePort
                .findById(new ProductId(command.productId))
                .orElseThrow();
        Offer offer = product.addOffer(
                new OfferUrl(command.offerUrl()),
                new Selector(command.selector(), SelectorType.valueOf(command.selectorType())),
                PageType.valueOf(command.pageType),
                new RefreshPeriod(command.refreshValue, ChronoUnit.valueOf(command.refreshUnit))
        );
        offerCrudPersistencePort.save(offer);
        return offer.getId();
    }

    public record CreateOfferCommand(
            String offerUrl,
            String productId,
            String selector,
            String selectorType,
            String pageType,
            int refreshValue,
            String refreshUnit
    ) {
    }
}
