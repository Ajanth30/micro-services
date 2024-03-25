package com.microservice.bookinventoryservice.service;


import com.microservice.bookinventoryservice.dto.BookDto;
import com.microservice.bookinventoryservice.dto.BookInventoryDto;
import com.microservice.bookinventoryservice.dto.BookResponseDto;
import com.microservice.bookinventoryservice.dto.SimpleResponse;
import com.microservice.bookinventoryservice.entity.Inventory;

public interface InventoryService {

    Inventory addBooKToInventory(BookInventoryDto bookInventoryDto);
    BookResponseDto getBookDetailsById(int bookId);

    SimpleResponse updateInventoryData(int bookId,BookInventoryDto bookInventoryDto);


}
