package com.microservice.bookinventoryservice.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookInventoryDto {
    private int bookId;
    private int availableQuantity;
    private double price;
}
