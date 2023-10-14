package com.library.librarymanagementsystem.Service;

import com.library.librarymanagementsystem.Dto.BookDto;
import com.library.librarymanagementsystem.Exception.BookNotFoundException;
import com.library.librarymanagementsystem.Exception.CanNotProcessBorrowingException;

public interface BorrowingService {
    public BookDto borrowBook(Long bookId,Long memberId) throws BookNotFoundException, CanNotProcessBorrowingException;
    public BookDto returnBook(Long bookId,Long memberId) throws BookNotFoundException;
    public boolean canBorrowBook(Long bookId,Long memberId);
}
