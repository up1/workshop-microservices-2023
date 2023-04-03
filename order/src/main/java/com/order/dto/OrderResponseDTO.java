package com.order.dto;

import com.order.entity.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class OrderResponseDTO {

    private UUID orderId;
    private Integer customerId;
    private UUID itemId;
    private Double amount;
    private OrderStatus status;
    private LocalDateTime created;

}
