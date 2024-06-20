package com.ludigi.product.adapter.in.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ludigi.product.ProductId;
import com.ludigi.product.port.in.ProductCommandPort;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureMockMvc
class CreateProductRestAdapterTest {
    @MockBean
    ProductCommandPort productCommandPort;
    @Autowired
    MockMvc mvc;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    void shouldCreateProduct() throws Exception {
        String productId = UUID.randomUUID().toString();
        when(productCommandPort.createProduct(any())).thenReturn(new ProductId(productId));

        CreateProductRestAdapter.CreateProductRequest createProductRequest = new CreateProductRestAdapter
                .CreateProductRequest("new product", "new product description");
        mvc.perform(
                post("/api/products")
                        .content(objectMapper.writeValueAsString(createProductRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(jwt()
                                .jwt(jwt -> jwt.subject(UUID.randomUUID().toString()))
                                .authorities(new SimpleGrantedAuthority("SCOPE_products.write"))
                        )
        ).andExpectAll(
                status().isCreated(),
                header().exists("location")
        );
    }
}