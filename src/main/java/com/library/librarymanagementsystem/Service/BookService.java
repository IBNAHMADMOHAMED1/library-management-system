package com.library.librarymanagementsystem.Service;

import com.library.librarymanagementsystem.Dto.BookDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
public interface BookService {
    BookDto addBook(BookDto bookDto);
}
