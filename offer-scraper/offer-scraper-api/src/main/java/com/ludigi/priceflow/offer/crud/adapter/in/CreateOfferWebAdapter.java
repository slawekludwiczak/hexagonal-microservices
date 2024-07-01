package com.ludigi.priceflow.offer.crud.adapter.in;

import com.ludigi.priceflow.offer.crud.port.in.CreateOfferUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
public class CreateOfferWebAdapter {
    private final CreateOfferUseCase createOfferUseCase;

    public CreateOfferWebAdapter(CreateOfferUseCase createOfferUseCase) {
        this.createOfferUseCase = createOfferUseCase;
    }

    @PostMapping("/api/offers")
    public ResponseEntity<?> createOffer(@RequestBody CreateOfferUseCase.CreateOfferCommand createOfferCommand) {
        UUID offerId = createOfferUseCase.createOffer(createOfferCommand);
        URI createdOfferUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(offerId)
                .toUri();
        return ResponseEntity.created(createdOfferUri).build();
    }

}
