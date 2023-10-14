package com.library.librarymanagementsystem.Repository;

import com.library.librarymanagementsystem.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
