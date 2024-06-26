package com.ludigi.product;

import com.ludigi.product.adapter.out.JpaProductPersistenceAdapter;
import com.ludigi.product.adapter.out.ProductEntityRepository;
import com.ludigi.product.port.in.ProductCommandPort;
import com.ludigi.product.port.in.ProductCrudService;
import com.ludigi.product.port.out.ProductPersistencePort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersistenceAdapterConfiguration {

    @Bean
    public ProductCommandPort createProductPort(ProductPersistencePort productPersistencePort) {
        return new ProductCrudService(productPersistencePort);
    }

    @Bean
    public ProductPersistencePort productPersistPort(ProductEntityRepository productEntityRepository) {
        return new JpaProductPersistenceAdapter(productEntityRepository);
    }
}
