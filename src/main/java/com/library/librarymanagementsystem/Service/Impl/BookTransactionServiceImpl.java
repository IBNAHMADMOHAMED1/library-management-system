package com.library.librarymanagementsystem.Service.Impl;

import com.library.librarymanagementsystem.Entity.BookTransaction;
import com.library.librarymanagementsystem.Repository.BookTransactionRepository;
import com.library.librarymanagementsystem.Service.BookTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookTransactionServiceImpl implements BookTransactionService {
    private final BookTransactionRepository bookTransactionRepository;
    @Override
    public Page<BookTransaction> getHistory(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return bookTransactionRepository.findAll(pageable);
    }
}
