package com.library.librarymanagementsystem.Service.Impl;

import com.library.librarymanagementsystem.Dto.BookDto;
import com.library.librarymanagementsystem.Repository.BookTransactionRepository;
import com.library.librarymanagementsystem.Repository.UserRepository;
import com.library.librarymanagementsystem.Service.BorrowingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class BorrowingServiceImpl implements BorrowingService {

    private final BookTransactionRepository bookTransactionRepository ;

    @Override
    public BookDto borrowBook(Long bookId, Long memberId) {
        return null;
    }

    @Override
    public BookDto returnBook(Long bookId, Long memberId) {
        return null;
    }

    @Override
    public boolean canBorrowBook(Long bookId, Long memberId){
        return  bookTransactionRepository.countAllByUser(memberId) <3 ;
    }
}
