package com.library.librarymanagementsystem.Entity;

import com.library.librarymanagementsystem.Enum.BookStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    private BookStatus status;
    private int quantity;
}
