package com.ludigi.product.adapter.in.rest;

import com.ludigi.product.ProductId;
import com.ludigi.product.port.in.CreateProductPort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.UUID;

@RestController
public class CreateProductRestAdapter {
    private final CreateProductPort createProductPort;

    public CreateProductRestAdapter(CreateProductPort createProductPort) {
        this.createProductPort = createProductPort;
    }

    @PostMapping("/api/products")
    ResponseEntity<?> createProduct(
            @RequestBody CreateProductRequest request,
            JwtAuthenticationToken authentication,
            @CurrentSecurityContext(expression = "authentication.principal") Jwt jwt
    ) {
        var createProductCommand = new CreateProductPort.CreateProductCommand(request.name, request.description);
        //TODO userId z security context
        UUID userId = UUID.fromString(jwt.getId());
        ProductId productId = createProductPort.createProduct(createProductCommand, userId);
        return ResponseEntity.created(
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(productId.id())
                        .toUri()
        ).build();
    }

    @GetMapping("/api/hello")
    String hello(@CurrentSecurityContext(expression = "authentication.principal") Jwt jwt) {
        return "hello";
    }

    record CreateProductRequest(
            String name,
            String description
    ) {
    }
}
