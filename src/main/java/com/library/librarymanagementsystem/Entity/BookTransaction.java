package com.library.librarymanagementsystem.Entity;


import com.library.librarymanagementsystem.Enum.BookTransactionStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Table(name = "book_transactions")
@Entity
@Setter
@Getter
@Builder
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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date borrowingDate ;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date returnDate ;

    @Column(columnDefinition = "ENUM('BORROWED','RETURNED','OVERDUE') DEFAULT 'BORROWED' ",name = "status",nullable = false)
    private BookTransactionStatus status;
}
