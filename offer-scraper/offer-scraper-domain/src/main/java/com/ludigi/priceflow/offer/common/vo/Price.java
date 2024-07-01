package com.ludigi.priceflow.offer.common.vo;

import java.time.LocalDateTime;

public record Price(double value, Currency currency, LocalDateTime time) {
}
