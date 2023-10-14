package com.library.librarymanagementsystem.Service.Impl;

import com.library.librarymanagementsystem.Dto.BookDto;
import com.library.librarymanagementsystem.Entity.Book;
import com.library.librarymanagementsystem.Entity.BookTransaction;
import com.library.librarymanagementsystem.Entity.Category;
import com.library.librarymanagementsystem.Enum.BookStatus;
import com.library.librarymanagementsystem.Exception.BookNotFoundException;
import com.library.librarymanagementsystem.Exception.CanNotProcessBorrowingException;
import com.library.librarymanagementsystem.Repository.BookRepository;
import com.library.librarymanagementsystem.Repository.BookTransactionRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class BorrowingServiceImplTest {

    @Mock
    BookTransactionRepository bookTransactionRepository;

    @InjectMocks
    BorrowingServiceImpl borrowingService;

    @Mock
    BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        Book book = Book.builder()
                .id(1L)
                .author("author")
                .isbn("isbn")
                .title("title")
                .category(Category.builder().name("category 1").build())
                .status(BookStatus.AVAILABLE)
                .build();
        BookTransaction bookTransaction = BookTransaction
                .builder()
                .book(book)
                .returnDate(Date.from(Instant.now().plus(10, ChronoUnit.DAYS)))
                .build();

        when(bookRepository.existsById(1L)).thenReturn(true);
        when(bookRepository.existsById(2L)).thenReturn(false);
        when(bookRepository.isAvailable(1L)).thenReturn(true);
        when(bookRepository.isAvailable(2L)).thenReturn(true);
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(bookTransactionRepository.countAllByUser(1L)).thenReturn(1L);
        when(bookTransactionRepository.countAllByUser(2L)).thenReturn(2L);
        when(bookTransactionRepository.countAllByUser(3L)).thenReturn(3L); // Exceeded limit
        when(bookTransactionRepository.save(bookTransaction)).thenReturn(bookTransaction);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void should_throw_book_not_found_exception_when_book_not_exist(){
        assertThrows(BookNotFoundException.class,()-> borrowingService.borrowBook(2L,1L));
    }

    @Test
    void should_throw_book_can_not_process_exception_when_book_not_available(){
        assertThrows(CanNotProcessBorrowingException.class,()-> borrowingService.borrowBook(1L,3L));
    }

    @Test
    void should_borrow_book_successfully() throws BookNotFoundException,CanNotProcessBorrowingException{
        BookDto bookDto = borrowingService.borrowBook(1L, 1L);
        assertNotNull(bookDto);
        assertEquals(bookDto.getTitle(),"title");


    }

    @Test
    void returnBook() {
    }

    @Test
    void should_return_false_because_the_member_exceeded() {
        boolean result = borrowingService.canBorrowBook(1L,3L);
        assertFalse(result);
    }
    @Test
    void should_return_true_because_the_member_() {
        boolean result = borrowingService.canBorrowBook(2L,3L);
        assertTrue(result);
    }
}