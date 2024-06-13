package com.ludigi.product.adapter.in.api;

import com.ludigi.product.ProductId;
import com.ludigi.product.port.in.CreateProductPort;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CreateProductRestAdapterTest {
    @MockBean
    CreateProductPort createProductPort;
    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    void shouldCreateProduct() {
        String id = UUID.randomUUID().toString();
        when(createProductPort.createProduct(any())).thenReturn(new ProductId(id));

        CreateProductRestAdapter.CreateProductRequest createProductRequest = new CreateProductRestAdapter.CreateProductRequest("new product", "new product description");
        ResponseEntity<Void> response = testRestTemplate.postForEntity("/api/products", createProductRequest, Void.class);
        URI location = response.getHeaders().getLocation();
        assertNotNull(location);
        String path = location.getPath();
        assertAll(
                () -> assertEquals(HttpStatus.CREATED, response.getStatusCode()),
                () -> assertEquals(
                        "/api/products/" + id,
                        path
                )
        );
    }
}