package com.microservice.bookdetailsservice.Controller;
import com.microservice.bookdetailsservice.Dto.BookDto;
import com.microservice.bookdetailsservice.Dto.Response;
import com.microservice.bookdetailsservice.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/add-book")
    public ResponseEntity<Response>addBook(@RequestBody BookDto bookDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.addBook(bookDto));
    }

    @GetMapping("/get-all")
    public  ResponseEntity<Response>getAllBooks(){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getAllBooks());
    }

    @GetMapping("/{book_id}")
    public ResponseEntity<BookDto>getBookById(@PathVariable("book_id") int id){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getBookById(id));
    }

    @PutMapping("/{book_id}/edit")
    public ResponseEntity<Response>editBook(@PathVariable("book_id")  int id,@RequestBody BookDto bookDto){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.updateBook(id,bookDto));
    }
    @DeleteMapping("/{book_id}/delete")
    public ResponseEntity<Response>deleteBook(@PathVariable("book_id") int id){
        return ResponseEntity.status(HttpStatus.GONE).body(bookService.deleteBookById(id));

    }




}
