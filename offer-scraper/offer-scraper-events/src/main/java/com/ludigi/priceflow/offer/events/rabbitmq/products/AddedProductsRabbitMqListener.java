package com.ludigi.priceflow.offer.events.rabbitmq.products;

import com.ludigi.priceflow.offer.common.events.ProductAddedEvent;
import com.ludigi.priceflow.offer.common.vo.ProductId;
import com.ludigi.priceflow.offer.crud.port.in.AddProductUseCase;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class AddedProductsRabbitMqListener {
    private final AddProductUseCase addProductUseCase;

    public AddedProductsRabbitMqListener(AddProductUseCase addProductUseCase) {
        this.addProductUseCase = addProductUseCase;
    }

    @RabbitListener(queues = ProductQueueRabbitMqConfig.queueName)
    public void receiveMessage(ProductAddedEvent event) {
        addProductUseCase.addProduct(new ProductId(event.id()));
    }
}
