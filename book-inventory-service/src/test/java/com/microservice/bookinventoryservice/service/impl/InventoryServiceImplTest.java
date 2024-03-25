package com.microservice.bookinventoryservice.service.impl;

import com.microservice.bookinventoryservice.dto.BookDto;
import com.microservice.bookinventoryservice.dto.BookInventoryDto;
import com.microservice.bookinventoryservice.dto.BookResponseDto;
import com.microservice.bookinventoryservice.dto.SimpleResponse;
import com.microservice.bookinventoryservice.entity.Inventory;
import com.microservice.bookinventoryservice.repository.InventoryRepository;
import com.microservice.bookinventoryservice.service.ApiClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.yaml.snakeyaml.scanner.ScannerImpl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;

import static net.bytebuddy.matcher.ElementMatchers.any;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class InventoryServiceImplTest {

    @Mock
    InventoryRepository mockInventoryRepository;

    @Mock
    ApiClientService mockApiClientService;

    @InjectMocks
    InventoryServiceImpl testInventoryService;

    Inventory inventory;
    BookInventoryDto bookInventoryDto;

    BookDto bookDto;
    BookResponseDto bookResponseDto;


    @BeforeEach
    void init(){
        inventory=new Inventory();
        bookInventoryDto=new BookInventoryDto();
        bookResponseDto=new BookResponseDto();
        bookDto=new BookDto();
    }


    @Test
    void addBookToInventory_shouldReturnInventoryObject(){

        Inventory expected=inventory;
        Mockito.when(mockInventoryRepository.save(any(Inventory.class))).thenReturn(inventory);
        Inventory actual=testInventoryService.addBooKToInventory(bookInventoryDto);

        assertAll(
                ()->assertNotNull(actual),
                ()->assertEquals(expected,actual)
        );

    }

    @Test
    void getBookDetailsById_shouldReturnBookDtoObject() {
        BookResponseDto expected=bookResponseDto;
        expected.setBookDto(bookDto);

        Mockito.when(mockApiClientService.getBookById(1)).thenReturn(bookDto);
        Mockito.when(mockInventoryRepository.findByBookId(1)).thenReturn(inventory);
        BookResponseDto actual=testInventoryService.getBookDetailsById(1);

        assertAll(
                ()->assertNotNull(actual),
                ()->assertEquals(expected,actual)
        );





    }

    @Test
    void updateInventoryData_shouldReturnSimpleResponse() {
        SimpleResponse expected= SimpleResponse.builder()
                .success(true)
                .message("updated successfully")
                .build();
        Mockito.when(mockInventoryRepository.findByBookId(1)).thenReturn(inventory);
        Mockito.when(mockInventoryRepository.save(inventory)).thenReturn(inventory);
        SimpleResponse actual=testInventoryService.updateInventoryData(1,bookInventoryDto);

        assertAll(
                ()->assertEquals(expected,actual)
        );


    }
}