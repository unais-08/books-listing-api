package com.booklist.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookResponseDTO {
    private Long id;
    private String title;
    private String author;
    private String summary;
    private String description;
    private String coverImageUrl;
}
