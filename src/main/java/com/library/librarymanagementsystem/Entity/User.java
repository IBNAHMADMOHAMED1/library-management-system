package com.library.librarymanagementsystem.Entity;

import com.library.librarymanagementsystem.Enum.ROLE;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private ROLE role;

    public User(String username, String password, ROLE role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

}
