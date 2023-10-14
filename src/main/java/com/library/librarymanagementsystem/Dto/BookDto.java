package com.library.librarymanagementsystem.Dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    @NotBlank(message = "book title should be filled")
    private String title ;
    @NotBlank(message = "book author can't be empty")
    private String author ;
    @NotBlank(message = "book isbn can't be empty")
    private String isbn ;
    @Min(0)
    private float price ;
}
