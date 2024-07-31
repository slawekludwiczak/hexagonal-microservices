package com.ludigi.priceflow.offer.scraping.scraper.jsoup;

import com.ludigi.priceflow.offer.scraping.scraper.HttpStatus;
import com.ludigi.priceflow.offer.scraping.scraper.Response;
import com.ludigi.priceflow.offer.scraping.scraper.exception.ScraperException;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.SimpleFileServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.net.InetSocketAddress;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class JsoupScraperTest {
    private static HttpServer server;
    private final JsoupScraper jsoupScraper = new JsoupScraper();

    @BeforeAll
    static void startTempServer() {
        var addr = new InetSocketAddress(8090);
        Path filesPath = Path.of(JsoupScraperTest.class.getResource("/com/ludigi/priceflow/offer/scraping/jsoup").getPath());
        server = SimpleFileServer.createFileServer(addr, filesPath, SimpleFileServer.OutputLevel.INFO);
        server.start();
    }

    @Test
    void shouldScrapeHtmlWhenPageExists() {
        Response response = jsoupScraper.fetchHtml("http://localhost:8090/page.html");
        assertEquals(HttpStatus.HTTP_20x, response.httpStatus());
    }

    @Test
    void shouldScrapeHtml() {
        assertThrows(
                ScraperException.class,
                () -> jsoupScraper.fetchHtml("http://localhost:8090/notexists.html")
        );
    }

    @AfterAll
    static void stopTempServer() {
        server.stop(0);
    }
}
