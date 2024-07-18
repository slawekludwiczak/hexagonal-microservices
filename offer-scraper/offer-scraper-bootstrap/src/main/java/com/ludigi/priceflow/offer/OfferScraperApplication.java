package com.ludigi.priceflow.offer;

import com.ludigi.priceflow.offer.common.events.EventPublisher;
import com.ludigi.priceflow.offer.crud.port.in.AddProductUseCase;
import com.ludigi.priceflow.offer.crud.port.in.CreateOfferUseCase;
import com.ludigi.priceflow.offer.crud.port.in.FindOffersUseCase;
import com.ludigi.priceflow.offer.crud.port.in.GetPriceHistoryUseCase;
import com.ludigi.priceflow.offer.crud.port.out.OfferCrudPersistencePort;
import com.ludigi.priceflow.offer.crud.port.out.PriceHistoryPersistencePort;
import com.ludigi.priceflow.offer.crud.port.out.ProductCrudPersistencePort;
import com.ludigi.priceflow.offer.scheduling.port.in.ScheduleOfferUseCase;
import com.ludigi.priceflow.offer.scheduling.port.out.OfferSchedulingPersistencePort;
import com.ludigi.priceflow.offer.scraping.port.in.FetchCurrentPriceUseCase;
import com.ludigi.priceflow.offer.scraping.port.out.OfferPersistencePort;
import com.ludigi.priceflow.offer.scraping.port.out.PricePointPersistencePort;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OfferScraperApplication {

    public static void main(String[] args) {
        SpringApplication.run(OfferScraperApplication.class, args);
    }


    @Bean
    ScheduleOfferUseCase scheduleOfferUseCase(
            OfferSchedulingPersistencePort offerSchedulingPersistencePort,
            EventPublisher eventPublisher) {
        return new ScheduleOfferUseCase(offerSchedulingPersistencePort, eventPublisher);
    }

    @Bean
    CreateOfferUseCase createOfferUseCase(ProductCrudPersistencePort productCrudPersistencePort,
                                          OfferCrudPersistencePort offerCrudPersistencePort) {
        return new CreateOfferUseCase(productCrudPersistencePort, offerCrudPersistencePort);
    }

    @Bean
    GetPriceHistoryUseCase getPriceHistoryUseCase(PriceHistoryPersistencePort priceHistoryPersistencePort) {
        return new GetPriceHistoryUseCase(priceHistoryPersistencePort);
    }

    @Bean
    FetchCurrentPriceUseCase fetchCurrentPriceUseCase(OfferPersistencePort offerPersistencePort,
                                                      PricePointPersistencePort pricePointPersistencePort) {
        return new FetchCurrentPriceUseCase(offerPersistencePort, pricePointPersistencePort);
    }

    @Bean
    AddProductUseCase addProductUseCase(ProductCrudPersistencePort productCrudPersistencePort) {
        return new AddProductUseCase(productCrudPersistencePort);
    }

    @Bean
    FindOffersUseCase findOffersUseCase(OfferCrudPersistencePort offerCrudPersistencePort) {
        return new FindOffersUseCase(offerCrudPersistencePort);
    }
}
