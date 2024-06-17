package com.ludigi.product;

import com.ludigi.product.adapter.out.JpaProductPersistenceAdapter;
import com.ludigi.product.adapter.out.ProductEntityRepository;
import com.ludigi.product.port.in.CreateProductPort;
import com.ludigi.product.port.out.ProductPersistPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersistenceAdapterConfiguration {

    @Bean
    public CreateProductPort createProductPort(ProductPersistPort productPersistPort) {
        return new ProductCrudService(productPersistPort);
    }

    @Bean
    public ProductPersistPort productPersistPort(ProductEntityRepository productEntityRepository) {
        return new JpaProductPersistenceAdapter(productEntityRepository);
    }
}
