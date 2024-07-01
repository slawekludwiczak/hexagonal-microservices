package com.ludigi.priceflow.offer.events.rabbitmq;

import com.ludigi.priceflow.offer.common.events.OfferScheduledEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import com.ludigi.priceflow.offer.common.events.EventPublisher;
import org.springframework.stereotype.Component;

@Component
public class RabbitEventPublisher implements EventPublisher {
    private final RabbitTemplate rabbitTemplate;

    public RabbitEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void publish(OfferScheduledEvent event) {
        rabbitTemplate.convertAndSend(
                RabbitMqConfig.topicExchangeName,
                "com.ludigi.priceflow.offers.scheduled",
                event
        );
    }
}
