package com.library.librarymanagementsystem.Controller;


import com.library.librarymanagementsystem.Service.BorrowingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/books")
@RequiredArgsConstructor
public class BorrowingController {

    private final BorrowingService borrowingService ;

    @PostMapping("/{id}/borrow")
    public String borrowBook(@PathVariable Long id){
        return "ok";
    }

    @PostMapping("/{id}/return")
    public String returnBook(@PathVariable Long id){
        return "ok";
    }
}
