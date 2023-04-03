package com.order.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class OrderRequestDTO {
    private UUID orderId;
    private Integer customerId;
    private UUID itemId;
}
