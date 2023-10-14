package com.library.librarymanagementsystem.Entity;

import com.library.librarymanagementsystem.Enum.BookStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private String isbn;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @Enumerated(EnumType.STRING)
    private BookStatus status;
    private int quantity;

    public Book(String title, String author, String isbn, Category category, BookStatus status, int quantity) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.category = category;
        this.status = status;
        this.quantity = quantity;
    }
}
