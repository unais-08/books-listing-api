package com.booklist.app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookRequestDTO {

    @NotBlank(message = "Title is required")
    @Size(min = 2, max = 100, message = "Title must be between 2 and 100 characters")
    private String title;

    @NotBlank(message = "Author is required")
    @Size(min = 2, max = 60, message = "Author must be between 2 and 60 characters")
    private String author;

    @Size(max = 150, message = "Summary cannot exceed 150 characters")
    private String summary;

    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;
}
