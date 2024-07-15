package com.ludigi.priceflow.product.port.out;

import com.ludigi.priceflow.product.events.ProductAddedEvent;

public interface EventPublisher {
    void publish(ProductAddedEvent event);
}
