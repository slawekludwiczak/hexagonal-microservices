package com.ludigi.priceflow.frontend.rest.client;

import com.ludigi.priceflow.frontend.config.feign.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@FeignClient(value = "priceflow-product-catalog", configuration = {FeignConfiguration.class})
public interface ProductRestClient {
    @GetMapping("/api/products/{id}")
    Optional<ProductResponse> findById(@PathVariable String id);
    @GetMapping("/api/products")
    List<ProductResponse> findAll();
    @PostMapping("/api/products")
    void addProduct(CreateProductRequest product);

    record ProductResponse(String id, String name, String description, String addedBy) { }
    record CreateProductRequest(String name, String description) { }
}
