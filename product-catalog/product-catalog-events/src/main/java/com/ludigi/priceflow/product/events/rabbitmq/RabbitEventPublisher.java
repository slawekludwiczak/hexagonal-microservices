package com.ludigi.priceflow.product.events.rabbitmq;

import com.ludigi.priceflow.product.events.ProductAddedEvent;
import com.ludigi.priceflow.product.port.out.EventPublisher;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class RabbitEventPublisher implements EventPublisher {
    private final RabbitTemplate rabbitTemplate;

    public RabbitEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void publish(ProductAddedEvent event) {
        rabbitTemplate.convertAndSend(
                RabbitMqConfig.topicExchangeName,
                "com.ludigi.priceflow.products.added",
                event
        );
    }
}
