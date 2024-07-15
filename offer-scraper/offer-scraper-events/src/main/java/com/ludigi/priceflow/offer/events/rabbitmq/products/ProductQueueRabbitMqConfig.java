package com.ludigi.priceflow.offer.events.rabbitmq.products;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ProductQueueRabbitMqConfig {
    static final String topicExchangeName = "priceflow-products";
    static final String queueName = "products-added";

    @Bean
    Queue productQueue() {
        return new Queue(queueName, false);
    }

    @Bean
    TopicExchange productExchange() {
        return new TopicExchange(topicExchangeName);
    }

    @Bean
    Binding productBinding(@Qualifier("productQueue") Queue productQueue,
                           @Qualifier("productExchange") TopicExchange productExchange) {
        return BindingBuilder
                .bind(productQueue)
                .to(productExchange)
                .with("com.ludigi.priceflow.products.#");
    }

}
