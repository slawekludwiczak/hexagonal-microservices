package com.ludigi.priceflow.frontend.products;

import com.ludigi.priceflow.frontend.config.feign.FeignConfiguration;
import com.ludigi.priceflow.frontend.config.feign.FeignErrorDecoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(value = "priceflow-product-catalog", configuration = {FeignConfiguration.class})
public interface ProductRestClient {
    @GetMapping("/api/products")
    List<ProductResponse> findAll();
    @PostMapping("/api/products")
    void addProduct(CreateProductRequest product);

    record ProductResponse(String id, String name, String description, String addedBy) { }
    record CreateProductRequest(String name, String description) { }
}
