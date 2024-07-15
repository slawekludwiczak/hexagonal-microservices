package com.ludigi.product.adapter.out;

import com.ludigi.priceflow.product.adapter.out.ProductEntity;
import com.ludigi.priceflow.product.adapter.out.ProductEntityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.orm.jpa.JpaSystemException;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class JpaProductPersistenceAdapterTest {
    @Autowired
    ProductEntityRepository productEntityRepository;

    @Test
    void shouldPersistCompleteEntity() {
        UUID productId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        ProductEntity productEntity = new ProductEntity(productId, "test", "test description", userId);
        productEntityRepository.save(productEntity);
        assertNotNull(productEntityRepository.findById(productId));
    }

    @Test
    void shouldThrowWhenPersistingEntityWithoutId() {
        ProductEntity productEntity = new ProductEntity();
        assertThrows(
                JpaSystemException.class,
                () -> productEntityRepository.save(productEntity)
        );
    }
}
