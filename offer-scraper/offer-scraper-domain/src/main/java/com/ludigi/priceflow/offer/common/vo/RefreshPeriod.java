package com.ludigi.priceflow.offer.common.vo;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public record RefreshPeriod(int value, ChronoUnit unit) {
    public RefreshPeriod {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
    }

    public Duration asDuration() {
        return Duration.of(value, unit);
    }
}
