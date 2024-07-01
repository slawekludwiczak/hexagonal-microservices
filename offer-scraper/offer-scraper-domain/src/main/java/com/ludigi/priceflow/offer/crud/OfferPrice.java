package com.ludigi.priceflow.offer.crud;

import com.ludigi.priceflow.offer.common.vo.Price;

import java.time.LocalDateTime;

public record OfferPrice(Price price, LocalDateTime time) {
}
