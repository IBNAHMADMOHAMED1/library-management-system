package com.library.librarymanagementsystem.Service;

import com.library.librarymanagementsystem.Dto.BookDto;

public interface BorrowingService {
    public BookDto borrowBook(Long bookId,Long memberId);
    public BookDto returnBook(Long bookId,Long memberId);
    public boolean canBorrowBook(Long bookId,Long memberId);
}
