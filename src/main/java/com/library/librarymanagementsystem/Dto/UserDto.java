package com.library.librarymanagementsystem.Dto;

import com.library.librarymanagementsystem.Enum.ROLE;
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
    private ROLE role;
}
