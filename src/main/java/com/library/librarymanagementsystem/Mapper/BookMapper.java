package com.library.librarymanagementsystem.Mapper;

import com.library.librarymanagementsystem.Dto.BookDto;
import com.library.librarymanagementsystem.Entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);
    BookDto toDto(Book book);
    Book toBook(BookDto bookDto);
}
