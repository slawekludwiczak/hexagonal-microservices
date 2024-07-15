package com.ludigi.product.adapter.in.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ludigi.priceflow.product.Product;
import com.ludigi.priceflow.product.ProductId;
import com.ludigi.priceflow.product.UserId;
import com.ludigi.priceflow.product.adapter.in.rest.FindProductRestAdapter;
import com.ludigi.priceflow.product.port.in.ProductQueryPort;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;

@WebMvcTest(FindProductRestAdapter.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class FindProductRestAdapterTest {
    @MockBean
    ProductQueryPort productQueryPort;
    @Autowired
    MockMvc mvc;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    void shouldFindProduct() throws Exception {
        String randomId = UUID.randomUUID().toString();
        Product product = Product.withId(
                new ProductId(randomId),
                "title",
                "desc",
                new UserId(UUID.randomUUID().toString())
        );
        when(productQueryPort.findById(randomId)).thenReturn(Optional.of(product));
        FindProductRestAdapter.ProductResponse response = new FindProductRestAdapter.ProductResponse(
                product.getId().id(),
                product.getName(),
                product.getDescription(),
                product.getAddedBy().id()
        );
        String jsonResponse = objectMapper.writeValueAsString(response);
        mvc.perform(MockMvcRequestBuilders.get("/api/products/{randomId}", randomId)
                        .with(jwt().authorities(new SimpleGrantedAuthority("SCOPE_products.read"))))
                .andExpectAll(
                        MockMvcResultMatchers.status().isOk(),
                        MockMvcResultMatchers.content().json(jsonResponse)
                );
    }
}
