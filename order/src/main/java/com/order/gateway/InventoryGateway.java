package com.order.gateway;

import com.order.dto.ItemDTO;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.UUID;

@Component
public class InventoryGateway {

    private static org.slf4j.Logger Logger = LoggerFactory.getLogger(InventoryGateway.class);

    @Autowired
    private WebClient webClient;

    @Autowired
    private ObservationRegistry registry;

    public Mono<ItemDTO> getInventoryItem(UUID itemId) {
        return Observation.createNotStarted("call-from-order-service", registry)
                .contextualName("call-from-order-service")
                .observe(() -> {
                    Logger.info("Will send a request to the server");
                    return this.webClient
                            .get()
                            .uri(String.format("/stock/deduct/%s", itemId))
                            .header("X-B3-TRACEID", MDC.get("X-B3-TraceId"))
                            .retrieve()
                            .bodyToMono(ItemDTO.class)
                            .timeout(Duration.ofMinutes(1l)); // Set timeout => resilience pattern
                });
    }
}
