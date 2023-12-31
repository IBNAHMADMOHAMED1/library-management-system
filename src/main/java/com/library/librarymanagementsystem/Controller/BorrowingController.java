package com.library.librarymanagementsystem.Controller;


import com.library.librarymanagementsystem.Dto.APIResponse;
import com.library.librarymanagementsystem.Dto.BookDto;
import com.library.librarymanagementsystem.Dto.ErrorDto;
import com.library.librarymanagementsystem.Exception.BookNotFoundException;
import com.library.librarymanagementsystem.Exception.CanNotProcessBorrowingException;
import com.library.librarymanagementsystem.Service.BorrowingService;
import com.library.librarymanagementsystem.Service.Impl.BookTransactionServiceImpl;
import com.library.librarymanagementsystem.Util.JwtUtil;
import com.library.librarymanagementsystem.filter.JwtAuthFilter;
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
    private final BookTransactionServiceImpl bookTransactionService;

    @PostMapping("/{id}/borrow")
    public ResponseEntity<APIResponse<?>> borrowBook(@PathVariable Long id){
        try {
            BookDto bookDto = borrowingService.borrowBook(id, 1L);
           return  ResponseEntity.status(HttpStatus.OK).body(
                    APIResponse.builder()
                            .status("success")
                            .result(bookDto)
                            .build()
            );
        } catch (BookNotFoundException e) {
           return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    APIResponse.builder()
                            .status("failed")
                            .errors(List.of(ErrorDto.builder().message(e.getMessage()).build()))
                            .build()
            );
        } catch (CanNotProcessBorrowingException e) {
          return   ResponseEntity.status(HttpStatus.NOT_EXTENDED).body(
                    APIResponse.builder()
                            .status("failed")
                            .errors(List.of(ErrorDto.builder().message(e.getMessage()).build()))
                            .build()
            );
        }
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

    @GetMapping("/history")
    public ResponseEntity<APIResponse<?>> getHistory(@RequestParam int page, @RequestParam int size){
        return ResponseEntity.ok(
                APIResponse.builder()
                        .status("success")
                        .result(bookTransactionService.getHistory(page,size))
                        .build()
        );
    }
}
