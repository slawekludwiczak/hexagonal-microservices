package com.ludigi.priceflow.offer.events.rabbitmq.offers;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class OffersQueueRabbitMqConfig {
    static final String topicExchangeName = "priceflow-offers";
    static final String queueName = "scheduled-offers";

    @Bean
    Queue offerQueue() {
        return new Queue(queueName, false);
    }

    @Bean
    TopicExchange offerExchange() {
        return new TopicExchange(topicExchangeName);
    }

    @Bean
    Binding offerBinding(@Qualifier("offerQueue") Queue offerQueue,
                         @Qualifier("offerExchange") TopicExchange offerExchange) {
        return BindingBuilder
                .bind(offerQueue)
                .to(offerExchange)
                .with("com.ludigi.priceflow.offers.#");
    }

}
