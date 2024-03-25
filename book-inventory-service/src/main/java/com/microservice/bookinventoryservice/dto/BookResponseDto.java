package com.microservice.bookinventoryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookResponseDto {
    private BookDto bookDto;
    private int availableQuantity;
    private double price;
    private LocalDateTime lastRestocked;
}
