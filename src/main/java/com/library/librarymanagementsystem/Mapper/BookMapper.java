package com.library.librarymanagementsystem.Mapper;

import com.library.librarymanagementsystem.Dto.BookDto;
import com.library.librarymanagementsystem.Entity.Book;
import com.library.librarymanagementsystem.Entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {
    public BookDto toDto(Book book){
        return BookDto.builder()
                .author(book.getAuthor())
                .isbn(book.getIsbn())
                .title(book.getTitle())
                .category(book.getCategory().getName())
                .status(book.getStatus().name())
                .build();
    }
   public  Book toBook(BookDto bookDto){
        return Book.builder()
                .author(bookDto.getAuthor())
                .category(Category.builder().name(bookDto.getCategory()).build())
                .title(bookDto.getTitle())
                .isbn(bookDto.getIsbn())
                .build();
    }
}
