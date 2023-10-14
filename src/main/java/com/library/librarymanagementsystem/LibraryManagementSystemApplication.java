package com.library.librarymanagementsystem;

import com.library.librarymanagementsystem.Entity.Book;
import com.library.librarymanagementsystem.Entity.BookTransaction;
import com.library.librarymanagementsystem.Entity.Category;
import com.library.librarymanagementsystem.Entity.User;
import com.library.librarymanagementsystem.Enum.BookStatus;
import com.library.librarymanagementsystem.Enum.ROLE;
import com.library.librarymanagementsystem.Repository.BookRepository;
import com.library.librarymanagementsystem.Repository.BookTransactionRepository;
import com.library.librarymanagementsystem.Repository.CategoryRepository;
import com.library.librarymanagementsystem.Repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class LibraryManagementSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(LibraryManagementSystemApplication.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, BookTransactionRepository bookTransactionRepository, BookRepository bookRepository, CategoryRepository categoryRepository) {
        return args -> {
            userRepository.save(new User("admin", "admin", ROLE.ADMIN));
           User user = userRepository.save(new User("user", "user", ROLE.MEMBER));
          // Category category = categoryRepository.save(new Category("category"));
          // Book book = bookRepository.save(new Book("book", "author", "isbn", category, BookStatus.AVAILABLE, 1));
          //  bookTransactionRepository.save(new BookTransaction(user, book, new Date(), new Date(), null));
        };
    }

}
