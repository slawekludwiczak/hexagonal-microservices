package com.ludigi.priceflow.offer.crud.port.in;

import com.ludigi.priceflow.offer.common.exception.OfferNotFoundException;
import com.ludigi.priceflow.offer.common.vo.PageType;
import com.ludigi.priceflow.offer.common.vo.RefreshPeriod;
import com.ludigi.priceflow.offer.common.vo.Selector;
import com.ludigi.priceflow.offer.common.vo.SelectorType;
import com.ludigi.priceflow.offer.crud.Offer;
import com.ludigi.priceflow.offer.crud.port.out.OfferCrudPersistencePort;

import java.time.temporal.ChronoUnit;
import java.util.UUID;

public class EditOfferUseCase {
    private final OfferCrudPersistencePort offerCrudPersistencePort;

    public EditOfferUseCase(OfferCrudPersistencePort offerCrudPersistencePort) {
        this.offerCrudPersistencePort = offerCrudPersistencePort;
    }

    public void editOffer(final UUID offerId, final EditOfferCommand command) {
        Offer offer = offerCrudPersistencePort.findById(offerId).orElseThrow(OfferNotFoundException::new);
        offer.setSelector(new Selector(command.selector, SelectorType.valueOf(command.selectorType)));
        offer.setPageType(PageType.valueOf(command.pageType));
        offer.setRefreshPeriod(new RefreshPeriod(command.refreshValue, ChronoUnit.valueOf(command.refreshUnit)));
        offerCrudPersistencePort.save(offer);
    }

    public record EditOfferCommand(String selector,
                                   String selectorType,
                                   String pageType,
                                   int refreshValue,
                                   String refreshUnit) {
    }
}
