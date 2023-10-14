package com.library.librarymanagementsystem.Service.Impl;

import com.library.librarymanagementsystem.Dto.BookDto;
import com.library.librarymanagementsystem.Dto.SearchBookRequest;
import com.library.librarymanagementsystem.Entity.Book;
import com.library.librarymanagementsystem.Entity.Category;
import com.library.librarymanagementsystem.Enum.BookStatus;
import com.library.librarymanagementsystem.Exception.BookAlreadyExistsException;
import com.library.librarymanagementsystem.Mapper.BookMapper;
import com.library.librarymanagementsystem.Repository.BookRepository;
import com.library.librarymanagementsystem.Repository.CategoryRepository;
import com.library.librarymanagementsystem.Service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final BookMapper bookMapper;

    @Override
    public BookDto addBook(@Valid BookDto bookDto) {
        // TODO: validate user type and throw exception if not ADMIN
        if (bookIsExist(bookDto.getIsbn())) {
            throw new BookAlreadyExistsException();
        }
        Book book = bookMapper.toBook(bookDto);
        Optional<Category> optionalCategory = categoryRepository.findByName(bookDto.getCategory());
        if (optionalCategory.isPresent()) {
            book.setCategory(optionalCategory.get());
        } else {
            book.setCategory(categoryRepository.save(Category.builder()
                    .name(bookDto.getCategory())
                    .build()
            ));
        }
        book.setStatus(BookStatus.AVAILABLE);
        bookRepository.save(book);
        return bookMapper.toDto(book);
    }

    @Override
    public Page<BookDto> searchBooks(@Valid SearchBookRequest request, int pageNumber, int pageSize) {
        // TODO: validate user type and throw exception if not MEMBER
        if (request.getTitle() == null && request.getAuthor() == null && request.getCategory() == null) {
            throw new IllegalArgumentException("At least one of the search parameters must be provided");
        }
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("title"));
        Category category = categoryRepository.findByName(request.getCategory()).orElse(null);
        return bookRepository.findAllByTitleOrAuthorOrCategory(request.getTitle(),
                        request.getAuthor(),
                        category,
                        pageable)
                .map(bookMapper::toDto);
    }


    private boolean bookIsExist(String isbn) {
        return bookRepository.findByIsbn(isbn).isPresent();
    }
}
