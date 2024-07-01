package com.ludigi.priceflow.offer.events.rabbitmq;

import com.ludigi.priceflow.offer.common.events.OfferScheduledEvent;
import com.ludigi.priceflow.offer.scraping.extractor.PriceExtractor;
import com.ludigi.priceflow.offer.scraping.extractor.jsoup.JsoupPriceExtractor;
import com.ludigi.priceflow.offer.scraping.port.in.FetchCurrentPriceUseCase;
import com.ludigi.priceflow.offer.scraping.scraper.Scraper;
import com.ludigi.priceflow.offer.scraping.scraper.jsoup.JsoupScraper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ScheduledOffersRabbitMqListener {
    private final FetchCurrentPriceUseCase fetchCurrentPriceUseCase;

    public ScheduledOffersRabbitMqListener(FetchCurrentPriceUseCase fetchCurrentPriceUseCase) {
        this.fetchCurrentPriceUseCase = fetchCurrentPriceUseCase;
    }

    @RabbitListener(queues = RabbitMqConfig.queueName)
    public void receiveMessage(OfferScheduledEvent event) {
        fetchCurrentPriceUseCase.fetchCurrentPrice(event.id());
    }
}