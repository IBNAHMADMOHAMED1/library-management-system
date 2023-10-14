package com.library.librarymanagementsystem.Controller;


import com.library.librarymanagementsystem.Dto.APIResponse;
import com.library.librarymanagementsystem.Service.BorrowingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BorrowingController {

    private final BorrowingService borrowingService ;

    @PostMapping("/{id}/borrow")
    public ResponseEntity<APIResponse<?>> borrowBook(@PathVariable Long id){
        borrowingService.borrowBook(id,1L);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/return")
    public ResponseEntity<APIResponse<?>> returnBook(@PathVariable Long id){
        borrowingService.returnBook(id,1L);
        return ResponseEntity.ok().build();
    }
}
