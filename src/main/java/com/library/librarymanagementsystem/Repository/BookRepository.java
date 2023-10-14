package com.library.librarymanagementsystem.Repository;

import com.library.librarymanagementsystem.Entity.Book;
import com.library.librarymanagementsystem.Entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByIsbn(String isbn);

    @Query("SELECT b FROM Book b WHERE b.title LIKE %?1% OR b.author LIKE %?1% OR b.category = ?2")
    Page<Book> findAllByTitleOrAuthorOrCategory(String title, String author, Category category, Pageable pageable);
    @Query("SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END FROM Book b WHERE b.id = :bookId and b.status='AVAILABLE'")
    boolean isAvailable(@Param("bookId") Long bookId);
}
