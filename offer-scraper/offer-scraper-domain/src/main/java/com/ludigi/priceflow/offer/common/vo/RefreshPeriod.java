package com.ludigi.priceflow.offer.common.vo;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public record RefreshPeriod(long value, ChronoUnit unit) {
    public RefreshPeriod {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
    }

    public Duration asDuration() {
        return Duration.of(value, unit);
    }

    public static RefreshPeriod from(Duration duration, ChronoUnit chronoUnit) {
        long value = switch (chronoUnit) {
            case MINUTES -> duration.toMinutes();
            case HOURS -> duration.toHours();
            case DAYS -> duration.toDays();
            default -> duration.toSeconds();
        };
        return new RefreshPeriod(value, chronoUnit);
    }
}
