package com.library.librarymanagementsystem.Service.Impl;

import com.library.librarymanagementsystem.Dto.BookDto;
import com.library.librarymanagementsystem.Entity.Book;
import com.library.librarymanagementsystem.Exception.BookAlreadyExistsException;
import com.library.librarymanagementsystem.Mapper.BookMapper;
import com.library.librarymanagementsystem.Repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookDto addBook(BookDto bookDto) {
        // TODO: validate user type and throw exception if not admin
        /*if (!bookDto.isValid()) {
            throw new RuntimeException("Book cannot be null");
        }
        bookRepository.findById(bookDto.getId()).ifPresent(book -> {
            throw new BookAlreadyExistsException();
        });*/
        Book book = bookRepository.save(bookMapper.toBook(bookDto));
        return bookMapper.toDto(book);
    }


}
