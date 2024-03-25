package com.microservice.bookdetailsservice.Service;

import com.microservice.bookdetailsservice.Dto.BookDto;
import com.microservice.bookdetailsservice.Dto.Response;
import com.microservice.bookdetailsservice.Entity.Book;


public interface BookService {

    Response addBook(BookDto bookDto);

    Response  getAllBooks();

    BookDto getBookById(int id);

    Response deleteBookById(int id);

    Response updateBook(int id,BookDto bookDto);



}
