package com.ludigi.priceflow.offer.scraping;

import java.time.LocalDateTime;

public record Price(double value, Currency currency, LocalDateTime time) {
}
