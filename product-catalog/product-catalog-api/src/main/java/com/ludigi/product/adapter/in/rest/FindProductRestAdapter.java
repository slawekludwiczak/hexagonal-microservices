package com.ludigi.product.adapter.in.rest;

import com.ludigi.product.Product;
import com.ludigi.product.port.in.ProductQueryPort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
class FindProductRestAdapter {
    private final ProductQueryPort productQueryPort;

    public FindProductRestAdapter(ProductQueryPort productQueryPort) {
        this.productQueryPort = productQueryPort;
    }

    @GetMapping("/api/products/{id}")
    ResponseEntity<ProductResponse> findProduct(@PathVariable("id") String id) {
        return productQueryPort.findById(id)
                .map(ProductResponse::new)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/api/products")
    ResponseEntity<List<ProductResponse>> findAllProducts(@CurrentSecurityContext(expression = "authentication.principal") Jwt jwt) {
        List<ProductResponse> products = productQueryPort.findAllProducts()
                .stream()
                .map(ProductResponse::new)
                .toList();
        return ResponseEntity.ok(products);
    }

    record ProductResponse(String id, String name, String description, String addedBy) {
        public ProductResponse(Product product) {
            this(product.getId().id(), product.getName(), product.getDescription(), product.getAddedBy().id());
        }
    }
}
