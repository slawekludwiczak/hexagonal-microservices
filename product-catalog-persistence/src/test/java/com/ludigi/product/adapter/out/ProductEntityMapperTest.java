package com.ludigi.product.adapter.out;

import com.ludigi.product.Product;
import com.ludigi.product.ProductId;
import com.ludigi.product.UserId;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ProductEntityMapperTest {

    @Test
    void shouldMapAllFieldsFromDomainObjectToEntity() {
        Product product = Product.withId(
                new ProductId(UUID.randomUUID().toString()),
                "test",
                "test description",
                new UserId(UUID.randomUUID().toString())
        );
        ProductEntity entity = ProductEntityMapper.from(product);
        assertAll(
                () -> assertEquals(product.getId().id(), entity.getId().toString()),
                () -> assertEquals(product.getName(), entity.getName()),
                () -> assertEquals(product.getDescription(), entity.getDescription()),
                () -> assertEquals(product.getAddedBy().id(), entity.getAddedBy().toString())
        );
    }

}