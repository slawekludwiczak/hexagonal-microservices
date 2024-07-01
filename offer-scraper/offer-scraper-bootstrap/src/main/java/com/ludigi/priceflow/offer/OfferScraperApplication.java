package com.ludigi.priceflow.offer;

import com.ludigi.priceflow.offer.crud.port.in.CreateOfferUseCase;
import com.ludigi.priceflow.offer.crud.port.out.OfferCrudPersistencePort;
import com.ludigi.priceflow.offer.scheduling.port.in.ScheduleOfferUseCase;
import com.ludigi.priceflow.offer.scheduling.port.out.OfferSchedulingPersistencePort;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OfferScraperApplication {

    public static void main(String[] args) {
        SpringApplication.run(OfferScraperApplication.class, args);
    }


    @Bean
    ScheduleOfferUseCase scheduleOfferUseCase(OfferSchedulingPersistencePort offerSchedulingPersistencePort) {
        return new ScheduleOfferUseCase(offerSchedulingPersistencePort);
    }

    @Bean
    CreateOfferUseCase createOfferUseCase(OfferCrudPersistencePort offerCrudPersistencePort) {
        return new CreateOfferUseCase(offerCrudPersistencePort);
    }
}