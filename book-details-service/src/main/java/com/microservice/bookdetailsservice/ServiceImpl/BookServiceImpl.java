package com.microservice.bookdetailsservice.ServiceImpl;

import com.microservice.bookdetailsservice.Dto.Response;
import com.microservice.bookdetailsservice.Service.BookService;
import com.microservice.bookdetailsservice.Dto.BookDto;
import com.microservice.bookdetailsservice.Entity.Book;
import com.microservice.bookdetailsservice.Exceptions.BookNotFoundException;
import com.microservice.bookdetailsservice.Repo.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private BookRepository bookRepository;



    @Override
    public Response addBook(BookDto bookDto) {
        Book book =mapper.map(bookDto,Book.class);
        bookRepository.save(book);
        return Response.success(book);

    }

    @Override
    public Response getAllBooks() {
        List<BookDto> bookDto=new ArrayList<>();
        bookRepository.findAll().forEach((book)->{
             bookDto.add(mapper.map(book, BookDto.class));
        });
        return Response.success(bookDto);
    }

    @Override
    public BookDto getBookById(int id) {
        Book book= bookRepository.findById(id).get();
        return BookDto.builder()
                .id(book.getId())
                .bookType(book.getBookType())
                .description(book.getDescription())
                .name(book.getName())
                .yearOfPublication(book.getYearOfPublication())
                .publisherId(book.getPublisher().getId())
                .publisherName(book.getPublisher().getPublisher())
                .build();
    }

    @Override
    public Response deleteBookById(int id) {
        try{
            bookRepository.deleteById(id);
            return Response.success("Deleted");

        }catch (Exception ex){
            throw new BookNotFoundException("no book with book id"+id);
        }

    }

    @Override
    public Response updateBook(int id, BookDto bookDto) {
        Book existingBook=bookRepository.findById(id).get();
        mapper.map(bookDto,existingBook);
        bookRepository.save(existingBook);
        return Response.success(existingBook);
    }
}
