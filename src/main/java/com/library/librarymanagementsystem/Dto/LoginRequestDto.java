package com.library.librarymanagementsystem.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class LoginRequestDto {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
