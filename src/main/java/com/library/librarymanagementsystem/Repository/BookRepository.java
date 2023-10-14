package com.library.librarymanagementsystem.Repository;

import com.library.librarymanagementsystem.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END FROM Book b WHERE b.id = :bookId and b.status='AVAILABLE'")
    boolean isAvailable(@Param("bookId") Long bookId);
}
