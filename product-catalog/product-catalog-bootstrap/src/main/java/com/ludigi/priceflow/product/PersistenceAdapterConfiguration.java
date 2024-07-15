package com.ludigi.priceflow.product;

import com.ludigi.priceflow.product.adapter.out.JpaProductPersistenceAdapter;
import com.ludigi.priceflow.product.adapter.out.ProductEntityRepository;
import com.ludigi.priceflow.product.port.in.ProductCrudService;
import com.ludigi.priceflow.product.port.out.EventPublisher;
import com.ludigi.priceflow.product.port.out.ProductPersistencePort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersistenceAdapterConfiguration {

    @Bean
    public ProductCrudService createProductPort(ProductPersistencePort productPersistencePort, EventPublisher eventPublisher) {
        return new ProductCrudService(productPersistencePort, eventPublisher);
    }

    @Bean
    public ProductPersistencePort productPersistPort(ProductEntityRepository productEntityRepository) {
        return new JpaProductPersistenceAdapter(productEntityRepository);
    }
}
