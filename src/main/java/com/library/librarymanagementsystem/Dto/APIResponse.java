package com.library.librarymanagementsystem.Dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class APIResponse<T> {
    private String status;
    private T result;
    private List<ErrorDto> errors;
}
