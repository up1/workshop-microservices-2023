package com.order.controller;

import com.order.dto.OrderRequestDTO;
import com.order.dto.OrderResponseDTO;
import com.order.entity.Order;
import com.order.service.OrderService;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("order")
public class OrderController {

    private static Logger logger = LoggerFactory.getLogger("jsonLogger");

    @Autowired
    private MeterRegistry meterRegistry;

    @Autowired
    private OrderService service;

    @PostMapping("/create")
    public Mono<Order> createOrder(@RequestBody OrderRequestDTO request) throws Exception{
        // Logging
        logger.info("Called create order");
        return this.service.createOrder(request);
    }

    @GetMapping("/all")
    public Flux<OrderResponseDTO> getAllOrders(){
        // Custom metric
        meterRegistry.counter("get_all_order").increment();
        Random random = new Random();
        meterRegistry.gauge("number_of_active_users",new AtomicInteger(0)).set(random.nextInt(100));

        // Logging
        logger.info("Called get all orders");

        return this.service.getAllOrders();
    }

}
