package com.order.repository;

import com.order.entity.Order;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface OrderRepository extends ReactiveCrudRepository<Order, UUID> {

    Mono<Order> findById(UUID orderId);
}
