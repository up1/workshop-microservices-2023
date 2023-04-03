package com.order.gateway;

import com.order.dto.ItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.UUID;

@Component
public class InventoryGateway {

    @Autowired
    private WebClient webClient;

    public Mono<ItemDTO> getInventoryItem(UUID itemId) {
        return this.webClient
                .get()
                .uri(String.format("/stock/%s", itemId))
                .retrieve()
                .bodyToMono(ItemDTO.class)
                .timeout(Duration.ofMinutes(1l)); // Set timeout => resilience pattern
    }
}
