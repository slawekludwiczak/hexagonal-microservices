package com.ludigi.product.adapter.in.rest;

import com.ludigi.product.ProductId;
import com.ludigi.product.port.in.ProductCommandPort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.UUID;

@RestController
public class CreateProductRestAdapter {
    private final ProductCommandPort productCommandPort;

    public CreateProductRestAdapter(ProductCommandPort productCommandPort) {
        this.productCommandPort = productCommandPort;
    }

    @PostMapping("/api/products")
    ResponseEntity<?> createProduct(
            @RequestBody CreateProductRequest request,
            @CurrentSecurityContext(expression = "authentication.principal") Jwt jwt
    ) {
        UUID userId = UUID.fromString(jwt.getSubject());
        var createProductCommand = new ProductCommandPort.CreateProductCommand(
                request.name,
                request.description,
                userId.toString()
        );
        ProductId productId = productCommandPort.createProduct(createProductCommand);
        return ResponseEntity.created(
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(productId.id())
                        .toUri()
        ).build();
    }

    record CreateProductRequest(
            String name,
            String description
    ) {
    }
}
