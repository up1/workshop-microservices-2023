package com.order.controller;

import com.order.dto.OrderRequestDTO;
import com.order.dto.OrderResponseDTO;
import com.order.entity.Order;
import com.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService service;

    @PostMapping("/create")
    public Mono<Order> createOrder(@RequestBody OrderRequestDTO request) throws Exception{
        return this.service.createOrder(request);
    }

    @GetMapping("/all")
    public Flux<OrderResponseDTO> getAllOrders(){
        return this.service.getAllOrders();
    }

}
