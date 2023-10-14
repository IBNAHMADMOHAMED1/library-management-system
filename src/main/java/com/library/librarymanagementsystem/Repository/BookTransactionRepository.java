package com.library.librarymanagementsystem.Repository;

import com.library.librarymanagementsystem.Entity.BookTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookTransactionRepository extends JpaRepository<BookTransaction,Long> {

    @Query(value = "select count(id) from book_transactions where borrower_id =:memberId and status='BORROWED'" ,nativeQuery = true)
    Long countAllByUser(Long memberId);
    @Query("SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END FROM BookTransaction b WHERE b.book.id = :bookId and b.borrower.id=:memberId and b.status='BORROWED'")
    boolean existsByBook_IdAnAndBorrower_Id(@Param("bookId") Long bookId,@Param("memberId") Long memberId);

    Optional<BookTransaction> findByBook_IdAndAndBorrower_Id(Long bookId,Long borrowerId);

}
