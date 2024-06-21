package com.ludigi.product;

import com.ludigi.product.port.in.ProductCommandPort;
import com.ludigi.product.port.in.ProductCrudService;
import com.ludigi.product.port.out.ProductPersistPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ProductCrudServiceTest {
    @Mock
    ProductPersistPort productPersistPort;
    @InjectMocks
    ProductCrudService productCrudService;

    @Test
    void shouldSaveProductAndReturnId() {
        ArgumentCaptor<Product> productCaptor = ArgumentCaptor.forClass(Product.class);
        Mockito.doNothing().when(productPersistPort).save(productCaptor.capture());

        ProductCommandPort.CreateProductCommand createProductCommand = new ProductCommandPort
                .CreateProductCommand("sample product", "sample description", UUID.randomUUID().toString());
        ProductId productId = productCrudService.createProduct(createProductCommand);
        Product persistedProduct = productCaptor.getValue();

        Mockito.verify(productPersistPort, Mockito.times(1)).save(persistedProduct);
        assertEquals(productId, persistedProduct.getId());
    }
}