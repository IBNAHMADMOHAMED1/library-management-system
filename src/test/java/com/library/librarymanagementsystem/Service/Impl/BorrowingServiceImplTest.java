package com.library.librarymanagementsystem.Service.Impl;

import com.library.librarymanagementsystem.Entity.Book;
import com.library.librarymanagementsystem.Entity.Category;
import com.library.librarymanagementsystem.Enum.BookStatus;
import com.library.librarymanagementsystem.Repository.BookRepository;
import com.library.librarymanagementsystem.Repository.BookTransactionRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
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
        when(bookRepository.existsById(1L)).thenReturn(true);
        when(bookRepository.isAvailable(1L)).thenReturn(true);
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(bookTransactionRepository.countAllByUser(1L)).thenReturn(1L);
        when(bookTransactionRepository.countAllByUser(2L)).thenReturn(2L
        when(bookTransactionRepository.countAllByUser(3L)).thenReturn(3L);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void borrowBook() {


    }

    @Test
    void returnBook() {
    }

    @Test
    void canBorrowBook() {
        boolean result = borrowingService.canBorrowBook(1L,3L);
        assertFalse(result);
    }
}