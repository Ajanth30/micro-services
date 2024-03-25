package com.microservice.bookdetailsservice.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {

    private int id;
    private String name;
    private String description;
    private Integer yearOfPublication;
    private String bookType;
    private Integer publisherId;

    private String publisherName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookDto bookDto = (BookDto) o;
        return Objects.equals(name, bookDto.name) && Objects.equals(description, bookDto.description) && Objects.equals(yearOfPublication, bookDto.yearOfPublication) && Objects.equals(bookType, bookDto.bookType) && Objects.equals(publisherId, bookDto.publisherId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, yearOfPublication, bookType, publisherId);
    }
}
