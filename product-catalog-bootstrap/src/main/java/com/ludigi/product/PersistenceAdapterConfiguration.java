package com.ludigi.product;

import com.ludigi.product.adapter.out.InMemoryProductPersistenceAdapter;
import com.ludigi.product.port.in.CreateProductPort;
import com.ludigi.product.port.out.ProductPersistPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersistenceAdapterConfiguration {

    @Bean
    public CreateProductPort createProductPort() {
        return new ProductCrudService(productPersistPort());
    }

    @Bean
    public ProductPersistPort productPersistPort() {
        return new InMemoryProductPersistenceAdapter();
    }
}
