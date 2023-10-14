package com.library.librarymanagementsystem.Entity;


import com.library.librarymanagementsystem.Enum.BookTransactionStatus;
import jakarta.persistence.*;

import java.util.Date;

@Table(name = "book_transactions")
@Entity
public class BookTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @ManyToOne
    @JoinColumn(name = "borrower_id")
    private User borrower ;


    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book ;

    @Temporal(TemporalType.DATE)
    @Column(columnDefinition = "DEFAULT CURRENT_TIMESTAMP")
    private Date borrowingDate ;
    @Temporal(TemporalType.DATE)
    private Date returnDate ;

    @Enumerated
    private BookTransactionStatus status;
}
