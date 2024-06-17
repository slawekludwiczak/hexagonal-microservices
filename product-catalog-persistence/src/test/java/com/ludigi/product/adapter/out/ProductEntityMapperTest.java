package com.ludigi.product.adapter.out;

import com.ludigi.product.Product;
import com.ludigi.product.ProductId;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ProductEntityMapperTest {

    @Test
    void shouldMapAllFieldsFromDomainObjectToEntity() {
        Product product = Product.withId(
                new ProductId(UUID.randomUUID().toString()),
                "test",
                "test description"
        );
        ProductEntity entity = ProductEntityMapper.from(product);
        assertAll(
                () -> assertEquals(product.getId().id(), entity.getId().toString()),
                () -> assertEquals(product.getName(), entity.getName()),
                () -> assertEquals(product.getDescription(), entity.getDescription())
        );
    }

}