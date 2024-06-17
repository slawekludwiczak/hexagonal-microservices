package com.ludigi.product.adapter.in.api;

import com.ludigi.product.ProductId;
import com.ludigi.product.port.in.CreateProductPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class CreateProductRestAdapter {
    private final CreateProductPort createProductPort;

    public CreateProductRestAdapter(CreateProductPort createProductPort) {
        this.createProductPort = createProductPort;
    }

    @PostMapping("/api/products")
    ResponseEntity<?> createProduct(@RequestBody CreateProductRequest request) {
        var createProductCommand = new CreateProductPort.CreateProductCommand(request.name, request.description);
        ProductId productId = createProductPort.createProduct(createProductCommand);
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
    ) { }
}
