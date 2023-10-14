package com.library.librarymanagementsystem.Controller;


import com.library.librarymanagementsystem.Dto.APIResponse;
import com.library.librarymanagementsystem.Dto.BookDto;
import com.library.librarymanagementsystem.Dto.ErrorDto;
import com.library.librarymanagementsystem.Exception.BookNotFoundException;
import com.library.librarymanagementsystem.Exception.CanNotProcessBorrowingException;
import com.library.librarymanagementsystem.Service.BorrowingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BorrowingController {

    private final BorrowingService borrowingService ;

    @PostMapping("/{id}/borrow")
    public ResponseEntity<APIResponse<?>> borrowBook(@PathVariable Long id){
        try {
            BookDto bookDto = borrowingService.borrowBook(id, 1L);
            ResponseEntity.status(HttpStatus.OK).body(
                    APIResponse.builder()
                            .status("success")
                            .result(bookDto)
                            .build()
            );
        } catch (BookNotFoundException e) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    APIResponse.builder()
                            .status("failed")
                            .errors(List.of(ErrorDto.builder().message(e.getMessage()).build()))
                            .build()
            );
        } catch (CanNotProcessBorrowingException e) {
            ResponseEntity.status(HttpStatus.NOT_EXTENDED).body(
                    APIResponse.builder()
                            .status("failed")
                            .errors(List.of(ErrorDto.builder().message(e.getMessage()).build()))
                            .build()
            );
        }
        return ResponseEntity.ok().build();

    }

    @PostMapping("/{id}/return")
    public ResponseEntity<APIResponse<?>> returnBook(@PathVariable Long id){
        BookDto bookDto =null;
        try {
            bookDto = borrowingService.returnBook(id, 1L);

        } catch (BookNotFoundException e) {
            ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(APIResponse.builder()
                    .errors(List.of(ErrorDto.builder().message(e.getMessage()).build())));
        }
        return ResponseEntity.ok(APIResponse.builder()
                .result(bookDto)
                        .status("sucess")
                .build());
    }
}
