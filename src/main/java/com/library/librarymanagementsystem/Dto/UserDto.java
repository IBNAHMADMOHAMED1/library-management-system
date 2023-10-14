package com.library.librarymanagementsystem.Dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String username;
    private String password;
    private String email;
    private String role;
}
