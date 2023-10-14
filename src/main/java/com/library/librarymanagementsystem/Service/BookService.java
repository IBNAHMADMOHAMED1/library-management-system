package com.library.librarymanagementsystem.Service;

import com.library.librarymanagementsystem.Dto.BookDto;
import com.library.librarymanagementsystem.Dto.SearchBookRequest;
import org.springframework.data.domain.Page;

public interface BookService {
    BookDto addBook(BookDto bookDto);

    Page<BookDto> searchBooks(SearchBookRequest searchBookRequest, int page, int size);
}
