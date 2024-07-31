package com.ludigi.priceflow.offer.scraping.scraper;

public enum HttpStatus {
    HTTP_20x,
    HTTP_30x,
    HTTP_40x,
    HTTP_50x;

    public static HttpStatus from(int status) {
        if (status > 505 || status < 200) {
            throw new IllegalArgumentException("Status %d is not supported ".formatted(status));
        }
        if (status / 500 > 0) {
            return HttpStatus.HTTP_50x;
        } else if (status / 400 > 0) {
            return HttpStatus.HTTP_40x;
        } else if (status / 300 > 0) {
            return HttpStatus.HTTP_30x;
        } else {
            return HttpStatus.HTTP_20x;
        }
    }
}
