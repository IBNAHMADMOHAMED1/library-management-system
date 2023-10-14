package com.library.librarymanagementsystem.Service.Impl;

import com.library.librarymanagementsystem.Dto.BookDto;
import com.library.librarymanagementsystem.Entity.Book;
import com.library.librarymanagementsystem.Entity.Category;
import com.library.librarymanagementsystem.Enum.BookStatus;
import com.library.librarymanagementsystem.Repository.BookRepository;
import com.library.librarymanagementsystem.Repository.CategoryRepository;
import com.library.librarymanagementsystem.Service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class BookServiceImplTest {
    @InjectMocks
    private BookServiceImpl bookService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddBook() {
        BookDto bookDto = BookDto.builder()
                .title("The Lord of the Rings")
                .author("J. R. R. Tolkien")
                .isbn("123456")
                .category("Fiction")
                .build();

        Category mockCategory = Category.builder()
                .id(1L)
                .name("Fiction")
                .build();

        when(categoryRepository.findByName("Fiction")).thenReturn(Optional.of(mockCategory));

        when(bookRepository.save(Mockito.any(Book.class))).thenAnswer(invocation -> {
            Book savedBook = invocation.getArgument(0);
            savedBook.setId(1L);
            return savedBook;
        });

        BookDto resultBookDto = bookService.addBook(bookDto);

        assertNotNull(resultBookDto);
        assertEquals("123456", resultBookDto.getIsbn());
        assertEquals("Fiction", resultBookDto.getCategory());
        assertEquals(BookStatus.AVAILABLE.name(), resultBookDto.getStatus());

        Mockito.verify(bookRepository).save(Mockito.any(Book.class));
    }

    @Test
    void searchBooks() {
    }
}