package com.order.service;


import com.order.dto.OrderRequestDTO;
import com.order.dto.OrderResponseDTO;
import com.order.entity.Order;
import com.order.entity.OrderStatus;
import com.order.gateway.InventoryGateway;
import com.order.repository.OrderRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class OrderService {

    private static org.slf4j.Logger Logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private InventoryGateway inventoryGateway;

    @Autowired
    private ModelMapper modelMapper;

    public Mono<Order> createOrder(OrderRequestDTO orderRequestDTO) throws Exception {
        return this.orderRepository.save(this.convertToEntity(orderRequestDTO))
                .doOnNext(e -> orderRequestDTO.setOrderId(e.getId()))
                .doOnNext(e ->
                {
                    Logger.info("Order created with Pending Status");
                    // TODO => Order created event
                });
    }

    private Order convertToEntity(OrderRequestDTO dto) {
        Order order = modelMapper.map(dto, Order.class);

        inventoryGateway.getInventoryItem(order.getItemId())
                .map(i ->
                {
                    if (i == null)
                        throw new RuntimeException("Item not found");
                    return i;
                })
                .doOnSuccess(c ->
                {
                    order.setPrice(c.getPrice());
                    order.setId(dto.getOrderId());
                    order.setStatus(OrderStatus.PENDING);
                    order.setCreated(LocalDateTime.now());
                })
                .block();

        return order;
    }

    public Flux<OrderResponseDTO> getAllOrders() {
        return this.orderRepository.findAll().map(this::convertToDTO);
    }

    private OrderResponseDTO convertToDTO(Order order) {
        OrderResponseDTO dto = modelMapper.map(order, OrderResponseDTO.class);
        dto.setOrderId(order.getId());
        dto.setAmount(order.getPrice());
        return dto;
    }
}
