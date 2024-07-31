package com.ludigi.priceflow.offer.scraping.scraper;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class HttpStatusTest {

    @ParameterizedTest
    @MethodSource("statusArgumentProvider")
    void shouldReturn20xFor200(int status, HttpStatus expectedResult) {
        HttpStatus result = HttpStatus.from(status);
        assertEquals(expectedResult, result);
    }

    static Stream<Arguments> statusArgumentProvider() {
        return Stream.of(
                arguments(200, HttpStatus.HTTP_20x),
                arguments(201, HttpStatus.HTTP_20x),
                arguments(202, HttpStatus.HTTP_20x),
                arguments(301, HttpStatus.HTTP_30x),
                arguments(302, HttpStatus.HTTP_30x),
                arguments(400, HttpStatus.HTTP_40x),
                arguments(401, HttpStatus.HTTP_40x),
                arguments(403, HttpStatus.HTTP_40x),
                arguments(404, HttpStatus.HTTP_40x),
                arguments(501, HttpStatus.HTTP_50x),
                arguments(503, HttpStatus.HTTP_50x)
        );
    }
}