package com.library.librarymanagementsystem.Dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorDto {
    private String field;
    private String message;
}
