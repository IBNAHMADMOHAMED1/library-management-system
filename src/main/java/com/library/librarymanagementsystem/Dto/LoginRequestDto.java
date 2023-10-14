package com.library.librarymanagementsystem.Dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequestDto {
    private String username;
    private String password;
}
