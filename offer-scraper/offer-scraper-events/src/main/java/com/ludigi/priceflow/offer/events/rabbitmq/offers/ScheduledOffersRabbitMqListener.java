package com.ludigi.priceflow.offer.events.rabbitmq.offers;

import com.ludigi.priceflow.offer.common.events.OfferScheduledEvent;
import com.ludigi.priceflow.offer.scraping.port.in.FetchCurrentPriceUseCase;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ScheduledOffersRabbitMqListener {
    private final FetchCurrentPriceUseCase fetchCurrentPriceUseCase;

    public ScheduledOffersRabbitMqListener(FetchCurrentPriceUseCase fetchCurrentPriceUseCase) {
        this.fetchCurrentPriceUseCase = fetchCurrentPriceUseCase;
    }

    @RabbitListener(queues = OffersQueueRabbitMqConfig.queueName)
    public void receiveMessage(OfferScheduledEvent event) {
        fetchCurrentPriceUseCase.fetchCurrentPrice(event.id());
    }
}
