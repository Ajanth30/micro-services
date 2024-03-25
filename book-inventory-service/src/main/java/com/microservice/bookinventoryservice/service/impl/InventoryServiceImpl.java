package com.microservice.bookinventoryservice.service.impl;

import com.microservice.bookinventoryservice.dto.BookDto;
import com.microservice.bookinventoryservice.dto.BookInventoryDto;
import com.microservice.bookinventoryservice.dto.BookResponseDto;
import com.microservice.bookinventoryservice.dto.SimpleResponse;
import com.microservice.bookinventoryservice.entity.Inventory;
import com.microservice.bookinventoryservice.repository.InventoryRepository;
import com.microservice.bookinventoryservice.service.ApiClientService;
import com.microservice.bookinventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;



@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final ApiClientService apiClientService;


    @Override
    public Inventory addBooKToInventory(BookInventoryDto bookInventoryDto) {
        Inventory inventory=new Inventory();
        BeanUtils.copyProperties(bookInventoryDto,inventory);
        inventory.setLastRestocked(LocalDateTime.now());
        return inventoryRepository.save(inventory);
    }

    @Override
    public BookResponseDto getBookDetailsById(int bookId) {
        BookDto bookDto=apiClientService.getBookById(bookId);
        Inventory bookInventoryDetails=inventoryRepository.findByBookId(bookId);
        return BookResponseDto.builder()
                .bookDto(bookDto)
                .price(bookInventoryDetails.getPrice())
                .availableQuantity(bookInventoryDetails.getAvailableQuantity())
                .lastRestocked(bookInventoryDetails.getLastRestocked())
                .build();
    }

    @Override
    public SimpleResponse updateInventoryData(int bookId,BookInventoryDto bookInventoryDto) {
        Inventory optionalBookData=inventoryRepository.findByBookId(bookId);
        optionalBookData.setPrice(bookInventoryDto.getPrice());
        optionalBookData.setBookId(bookInventoryDto.getBookId());
        if(bookInventoryDto.getAvailableQuantity()!=optionalBookData.getAvailableQuantity()){
            optionalBookData.setAvailableQuantity(bookInventoryDto.getAvailableQuantity());
            optionalBookData.setLastRestocked(LocalDateTime.now());
        }
        inventoryRepository.save(optionalBookData);
        return SimpleResponse.builder()
                .message("updated successfully")
                .success(true)
                .build();
    }


}
