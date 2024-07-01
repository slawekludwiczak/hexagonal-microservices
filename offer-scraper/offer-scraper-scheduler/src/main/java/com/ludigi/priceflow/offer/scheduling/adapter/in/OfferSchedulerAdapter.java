package com.ludigi.priceflow.offer.scheduling.adapter.in;

import com.ludigi.priceflow.offer.scheduling.port.in.ScheduleOfferUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.concurrent.TimeUnit;

@Service
public class OfferSchedulerAdapter {
    private final ScheduleOfferUseCase offerSchedulerUseCase;
    private final Logger logger = LoggerFactory.getLogger(OfferSchedulerAdapter.class);

    public OfferSchedulerAdapter(ScheduleOfferUseCase offerSchedulerUseCase) {
        this.offerSchedulerUseCase = offerSchedulerUseCase;
    }

    @Scheduled(fixedRate = 5, timeUnit = TimeUnit.SECONDS)
    public void offerScheduler() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        offerSchedulerUseCase.scheduleOffers();
        stopWatch.stop();
        logger.debug("Scheduling offers took %s ms".formatted(stopWatch.getTotalTimeMillis()));
    }
}
