package com.microservice.bookinventoryservice.controller;

import com.microservice.bookinventoryservice.dto.BookInventoryDto;
import com.microservice.bookinventoryservice.dto.BookResponseDto;
import com.microservice.bookinventoryservice.dto.SimpleResponse;
import com.microservice.bookinventoryservice.entity.Inventory;
import com.microservice.bookinventoryservice.service.InventoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class InventoryControllerTest {

    private Inventory inventory;

    private BookResponseDto bookResponseDto;

    private BookInventoryDto bookInventoryDto;

    private SimpleResponse simpleResponse;

    @Mock
    InventoryService inventoryMockService;

    @InjectMocks
    InventoryController testInventoryController;

    @BeforeEach
    void init(){
        inventory=new Inventory();
        bookResponseDto=new BookResponseDto();
        simpleResponse=new SimpleResponse();
        bookInventoryDto=new BookInventoryDto();

    }

    @Test
    void add_shouldAddBookToInventorySuccessfully() {
        Inventory expected=inventory;

        //mocking service
        Mockito.when(inventoryMockService.addBooKToInventory(bookInventoryDto)).thenReturn(inventory);

        ResponseEntity<Inventory> response=testInventoryController.addBookToInventory(bookInventoryDto);
        Inventory actual=response.getBody();

        assertAll(
                ()->assertNotNull(actual),
                ()->assertEquals(expected,actual),
                ()->assertEquals(HttpStatus.CREATED,response.getStatusCode())
        );
    }

    @Test
    void getBookDetailsById_shouldReturnBookDto() {
        BookResponseDto expected=bookResponseDto;

        Mockito.when(inventoryMockService.getBookDetailsById(1)).thenReturn(bookResponseDto);
        ResponseEntity<BookResponseDto> response=testInventoryController.getBookDetailsById(1);
        BookResponseDto actual=response.getBody();
        assertAll(
                ()->assertNotNull(actual),
                ()->assertEquals(expected,actual),
                ()->assertEquals(HttpStatus.OK,response.getStatusCode())
        );
    }


    @Test
    void editInventoryData() {
        SimpleResponse expected=simpleResponse;

        Mockito.when(inventoryMockService.updateInventoryData(1,bookInventoryDto)).thenReturn(simpleResponse);
        ResponseEntity<SimpleResponse> response=testInventoryController.editInventoryData(1,bookInventoryDto);
        SimpleResponse actual=response.getBody();

        assertAll(
                ()->assertNotNull(actual),
                ()->assertEquals(expected,actual),
                ()->assertEquals(HttpStatus.OK,response.getStatusCode())
        );
    }
}