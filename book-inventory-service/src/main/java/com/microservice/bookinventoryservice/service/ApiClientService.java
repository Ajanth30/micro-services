package com.microservice.bookinventoryservice.service;


import com.microservice.bookinventoryservice.dto.BookDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(value = "BOOK-DETAIL-SERVICE", url = "http://localhost:8081/api/v1/")
public interface ApiClientService {

    @GetMapping(value = "book/{book_id}")
    BookDto getBookById(@PathVariable("book_id") int bookId);


}

