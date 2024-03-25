package com.microservice.bookinventoryservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    private int id;

    @NotNull(message = "name should not be null")
    private String name;

    private String description;

    @NotNull(message = "year of publication should not be null")
    @Min(value = 1000,message = "year of publication should be greater than 1000")
    private Integer yearOfPublication;

    @NotNull(message = "book type should not be null")
    private String bookType;

    private int publisherId;

    @NotNull(message = "publisher name should not be null")
    private String publisherName;

}
