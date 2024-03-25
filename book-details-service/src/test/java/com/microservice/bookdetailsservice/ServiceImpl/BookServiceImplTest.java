package com.microservice.bookdetailsservice.ServiceImpl;

import com.microservice.bookdetailsservice.Dto.BookDto;
import com.microservice.bookdetailsservice.Dto.Response;
import com.microservice.bookdetailsservice.Entity.Book;
import com.microservice.bookdetailsservice.Entity.Publisher;
import com.microservice.bookdetailsservice.Exceptions.BookNotFoundException;
import com.microservice.bookdetailsservice.Repo.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

    private Book book;
    private BookDto bookDto;

    private Publisher publisher;

    @Mock
    BookRepository mockBookRepository;

    @Mock
    ModelMapper mockMapper;

    @InjectMocks
    BookServiceImpl testBookService;

    @BeforeEach
    void  initiate(){
         book=new Book();
         bookDto=new BookDto();
         publisher=new Publisher();
    }

    @Test
    void addBook_shouldCreateNewBook() {
        Response expected=Response.success(book);

        //mocking map method
        when(mockMapper.map(bookDto,Book.class)).thenReturn(book);

        // mocking save method
        when(mockBookRepository.save(any())).thenReturn(book);

        Response actual=testBookService.addBook(bookDto);

        assertAll(
                ()->assertNotNull(actual),
                ()->assertEquals(expected,actual)
        );


    }

    @Test
    void getAllBooks_shouldReturnBooDtoList() {
        List<BookDto> bookDtoList=List.of(bookDto);
        List<Book> bookList=List.of(book);
        Response expected=Response.success(bookDtoList);

        //mocking map method
        when(mockMapper.map(book,BookDto.class)).thenReturn(bookDto);

        //mocking repo method
        when(mockBookRepository.findAll()).thenReturn(bookList);
        Response actual=testBookService.getAllBooks();

        assertAll(
                ()->assertEquals(expected,actual),
                ()->assertNotNull(actual)
        );
    }

    @Test
    void getBookById_shouldReturnBook() {

        book.setPublisher(publisher);
        BookDto expected=bookDto;

        //repo mock method
        when(mockBookRepository.findById(1)).thenReturn(Optional.of(book));

        BookDto actual=testBookService.getBookById(1);

        assertAll(
                ()->assertNotNull(actual),
                ()->assertEquals(expected,actual)
        );
    }

    @Test
    void deleteBookById_shouldReturnSuccessMessage() {
        Response expected=Response.success("Deleted");
        Response actual=testBookService.deleteBookById(1);

        assertAll(
                ()->assertEquals(expected,actual)
        );


    }
    @Test
    void deleteBookById_ShouldThrowBookNotFoundException(){
        doThrow(BookNotFoundException.class).when(mockBookRepository).deleteById(1);
        BookNotFoundException exception=assertThrows(BookNotFoundException.class,()->testBookService.deleteBookById(1));
        assertTrue(exception.getMessage().contains("no book with book id"+1));
    }

@Test
void updateBook_shouldReturnUpdatedBook() {
    // Arrange
    Book book = new Book();
    book.setId(1);

    BookDto bookDto = new BookDto();
    bookDto.setId(1);
    bookDto.setName("updated");

    Book updatedBook = new Book();
    updatedBook.setId(1);
    updatedBook.setName("updated");

    // Mocking repository and mapper
    when(mockBookRepository.findById(1)).thenReturn(Optional.of(book));
    when(mockMapper.map(bookDto, Book.class)).thenReturn(updatedBook);
    when(mockBookRepository.save(updatedBook)).thenReturn(updatedBook);

    // Act
    Response actual = testBookService.updateBook(1, bookDto);

    // Assert
    Response expected = Response.success(updatedBook);
    assertNotNull(actual);
    assertEquals(expected, actual);
}




}