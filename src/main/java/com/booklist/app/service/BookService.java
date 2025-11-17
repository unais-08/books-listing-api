package com.booklist.app.service;

import java.util.List;

import com.booklist.app.dto.BookRequestDTO;
import com.booklist.app.dto.BookResponseDTO;

public interface BookService {
    BookResponseDTO createBook(BookRequestDTO dto);

    BookResponseDTO getBook(Long id);

    List<BookResponseDTO> getAllBooks();

    BookResponseDTO updateBook(Long id, BookRequestDTO dto);

    void deleteBook(Long id);
}
