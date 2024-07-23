package com.ludigi.priceflow.offer.crud.adapter.in;

import com.ludigi.priceflow.offer.crud.port.in.EditOfferUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class EditOfferWebAdapter {
    private final EditOfferUseCase editOfferUseCase;


    public EditOfferWebAdapter(EditOfferUseCase editOfferUseCase) {
        this.editOfferUseCase = editOfferUseCase;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PatchMapping("/api/offers/{id}")
    void updateOffer(@PathVariable("id") UUID offerId,
                     @RequestBody EditOfferUseCase.EditOfferCommand editOfferCommand) {
        editOfferUseCase.editOffer(offerId, editOfferCommand);
    }
}
