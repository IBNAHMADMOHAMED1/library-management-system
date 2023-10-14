package com.library.librarymanagementsystem.Service.Impl;

import com.library.librarymanagementsystem.Dto.BookDto;
import com.library.librarymanagementsystem.Entity.Book;
import com.library.librarymanagementsystem.Exception.BookAlreadyExistsException;
import com.library.librarymanagementsystem.Mapper.BookMapper;
import com.library.librarymanagementsystem.Repository.BookRepository;
import com.library.librarymanagementsystem.Service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public BookDto addBook(@Valid BookDto bookDto) {
        // TODO: validate user type and throw exception if not admin
        if (isExist(bookDto.getIsbn())) {
            throw new BookAlreadyExistsException();
        }
        Book book = bookRepository.save(bookMapper.toBook(bookDto));
        return bookMapper.toDto(book);
    }



    private boolean isExist(String isbn) {
        return bookRepository.findByIsbn(isbn).isPresent();
    }
}
