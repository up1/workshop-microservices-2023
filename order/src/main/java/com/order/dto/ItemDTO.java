package com.order.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ItemDTO {
    private UUID id;
    private Integer itemId;
    private Double price;
    private Integer stockAvailable;
}
