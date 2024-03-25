package com.microservice.bookdetailsservice.Controller;

import com.microservice.bookdetailsservice.Dto.BookDto;
import com.microservice.bookdetailsservice.Dto.Response;
import com.microservice.bookdetailsservice.Dto.ResponseEnum;
import com.microservice.bookdetailsservice.Entity.Book;
import com.microservice.bookdetailsservice.Service.BookService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookControllerTest {

    @Mock
    BookService mockBookService;

    @InjectMocks
    BookController controllerTest;

    @BeforeAll
    static void init(){
        Book book=new Book();
        BookDto bookDto=new BookDto();
    }

    @Test
    void add_shouldAddBookSuccessfully() {

        Book book=new Book();
        book.setName("sampleBook");
        book.setBookType("sample");
        Response expected=new Response(ResponseEnum.SUCCESS,book);

        when(mockBookService.addBook(any())).thenReturn(expected);

        //when
        BookDto bookDto=new BookDto();
        ResponseEntity<Response> response=controllerTest.addBook(bookDto);
        Response actual=response.getBody();

        //then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(HttpStatus.CREATED, response.getStatusCode()),
                () -> assertEquals(expected,actual)
        );




    }

    @Test
    void getAllBooks_shouldReturnBookDtoList() {

        BookDto bookDto=new BookDto();
        Response expected= new Response(ResponseEnum.SUCCESS, List.of(bookDto));

        when(mockBookService.getAllBooks()).thenReturn(expected);

        //when
        ResponseEntity<Response> response=controllerTest.getAllBooks();
        Response actual= response.getBody();

        //then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(expected,actual)
        );


    }

    @Test
    void getBookById_shouldReturnBook() {
        BookDto expected= BookDto.builder().build();

        when(mockBookService.getBookById(anyInt())).thenReturn(expected);

        //when
        ResponseEntity<BookDto> response=controllerTest.getBookById(1);
        BookDto actual= response.getBody();

        //then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(expected,actual)
        );
    }

    @Test
    void editBook_shouldReturnBook(){
        BookDto bookDto=new BookDto();
        Book book=new Book();
        when(mockBookService.updateBook(1,bookDto)).thenReturn(Response.success(book));

        //when
        ResponseEntity<Response> response=controllerTest.editBook(1,bookDto);
        Response actual=response.getBody();

        //then
        assertAll(
                ()->assertNotNull(actual),
                ()->assertEquals(Response.success(book),actual),
                ()->assertEquals(HttpStatus.OK,response.getStatusCode())
        );
    }


    @Test
    void deleteBook_shouldGiveSuccessMessage() {

        when(mockBookService.deleteBookById(1)).thenReturn(Response.success("deleted"));

        //when
        ResponseEntity<Response> response=controllerTest.deleteBook(1);
        Response actual=response.getBody();

        //then
        assertAll(
                ()->assertEquals(Response.success("deleted"),actual)
        );

    }
}