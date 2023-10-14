package com.library.librarymanagementsystem.Entity;


import jakarta.persistence.*;

@Table(name = "book_transactions")
@Entity
public class BookTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @ManyToOne
    @JoinColumn(name = "borrower_id")
    private User borrower ;

}
