package com.library.librarymanagementsystem.Controller;

import com.library.librarymanagementsystem.Dto.BookDto;
import com.library.librarymanagementsystem.Dto.SearchBookRequest;
import com.library.librarymanagementsystem.Service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<BookDto> add(@RequestBody BookDto bookDto) {
        return ResponseEntity.ok(bookService.addBook(bookDto));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<BookDto>> search(@RequestBody SearchBookRequest searchBookRequest,
                                                @RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(bookService.searchBooks(searchBookRequest, page, size));
    }
}
