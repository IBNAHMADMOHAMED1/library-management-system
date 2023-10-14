package com.library.librarymanagementsystem.Service;

import com.library.librarymanagementsystem.Dto.BookDto;
import com.library.librarymanagementsystem.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class BorrowingServiceImpl implements BorrowingService{

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
        return false;
    }
}
