package com.library.librarymanagementsystem.Service;

import com.library.librarymanagementsystem.Entity.BookTransaction;
import org.springframework.data.domain.Page;

public interface BookTransactionService {
    Page<BookTransaction> getHistory(int page, int size);
}
