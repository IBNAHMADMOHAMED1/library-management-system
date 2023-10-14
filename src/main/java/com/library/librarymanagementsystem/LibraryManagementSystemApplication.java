package com.library.librarymanagementsystem;

import com.library.librarymanagementsystem.Entity.User;
import com.library.librarymanagementsystem.Enum.ROLE;
import com.library.librarymanagementsystem.Repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LibraryManagementSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(LibraryManagementSystemApplication.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository) {
        return args -> {
            userRepository.save(new User("admin", "admin", ROLE.ADMIN));
            userRepository.save(new User("user", "user", ROLE.MEMBER));
        };
    }

}
