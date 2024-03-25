package com.microservice.bookinventoryservice.controller;

import com.microservice.bookinventoryservice.dto.BookInventoryDto;
import com.microservice.bookinventoryservice.dto.BookResponseDto;
import com.microservice.bookinventoryservice.dto.SimpleResponse;
import com.microservice.bookinventoryservice.entity.Inventory;
import com.microservice.bookinventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/api/v1/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping("/add-book/{book_id}")
    public ResponseEntity<Inventory> addBookToInventory(@RequestBody BookInventoryDto bookInventoryDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(inventoryService.addBooKToInventory(bookInventoryDto));

    }

    @GetMapping("/book/{book_id}")
    public ResponseEntity<BookResponseDto> getBookDetailsById(@PathVariable("book_id")int bookId){
        return ResponseEntity.ok(inventoryService.getBookDetailsById(bookId));
    }

    @PutMapping("/book/{book_id}")
    public ResponseEntity<SimpleResponse> editInventoryData(@PathVariable("book_id")int bookId,@RequestBody BookInventoryDto bookInventoryDto){
        return ResponseEntity.ok(inventoryService.updateInventoryData(bookId,bookInventoryDto));
    }






}
