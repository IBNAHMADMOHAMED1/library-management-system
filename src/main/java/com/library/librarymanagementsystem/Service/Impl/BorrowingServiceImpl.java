package com.library.librarymanagementsystem.Service.Impl;

import com.library.librarymanagementsystem.Dto.BookDto;
import com.library.librarymanagementsystem.Entity.BookTransaction;
import com.library.librarymanagementsystem.Exception.BookNotFoundException;
import com.library.librarymanagementsystem.Exception.CanNotProcessBorrowingException;
import com.library.librarymanagementsystem.Mapper.BookMapper;
import com.library.librarymanagementsystem.Repository.BookRepository;
import com.library.librarymanagementsystem.Repository.BookTransactionRepository;
import com.library.librarymanagementsystem.Repository.UserRepository;
import com.library.librarymanagementsystem.Service.BorrowingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
@RequiredArgsConstructor
public class BorrowingServiceImpl implements BorrowingService {

    private final BookTransactionRepository bookTransactionRepository ;
    private final BookRepository bookRepository;
    private final UserRepository userRepository ;
    private final BookMapper bookMapper ;

    @Override
    public BookDto borrowBook(Long bookId, Long memberId) throws BookNotFoundException, CanNotProcessBorrowingException {
        if (!bookRepository.existsById(bookId)) throw new BookNotFoundException("cannot find book with id "+bookId);
        else if (!canBorrowBook(bookId,memberId)) throw  new CanNotProcessBorrowingException("can't borrow this book exceeted limit");
        BookTransaction newTransaction = BookTransaction.builder()
                .book(bookRepository.findById(bookId).get())
                .borrower(userRepository.findById(memberId).get())
                .borrowingDate(new Date())
                        .build();
        BookTransaction bookTransaction = bookTransactionRepository.save(newTransaction);
        return bookMapper.toDto(bookTransaction.getBook());
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
