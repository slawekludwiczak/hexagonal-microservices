package com.ludigi.product.adapter.out;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.jpa.JpaSystemException;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class JpaProductPersistenceAdapterTest {
    @Autowired
    ProductEntityRepository productEntityRepository;

    @Test
    void shouldPersistCompleteEntity() {
        UUID randomUUID = UUID.randomUUID();
        ProductEntity productEntity = new ProductEntity(randomUUID, "test", "test description");
        productEntityRepository.save(productEntity);
        assertNotNull(productEntityRepository.findById(randomUUID));
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