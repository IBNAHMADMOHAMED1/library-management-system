package com.library.librarymanagementsystem.Repository;

import com.library.librarymanagementsystem.Entity.BookTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookTransactionRepository extends JpaRepository<BookTransaction,Long> {

    @Query(value = "select count(id) from book_transactions where borrower_id =:memberId and status='BORROWED'" ,nativeQuery = true)
    Long countAllByUser(Long memberId);
}
