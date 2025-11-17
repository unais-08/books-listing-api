package com.booklist.app.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.booklist.app.dto.BookRequestDTO;
import com.booklist.app.dto.BookResponseDTO;
import com.booklist.app.entity.Book;
import com.booklist.app.exception.BookNotFoundException;
import com.booklist.app.repository.BookRepository;
import com.booklist.app.service.BookService;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookResponseDTO createBook(BookRequestDTO dto) {
        Book book = Book.builder()
                .title(dto.getTitle())
                .author(dto.getAuthor())
                .summary(dto.getSummary())
                .description(dto.getDescription())
                .coverImageUrl("default.png")
                .build();

        Book saved = bookRepository.save(book);
        return mapToDTO(saved);
    }

    @Override
    public BookResponseDTO getBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + id));
        return mapToDTO(book);
    }

    @Override
    public List<BookResponseDTO> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BookResponseDTO updateBook(Long id, BookRequestDTO dto) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setSummary(dto.getSummary());
        book.setDescription(dto.getDescription());

        Book updated = bookRepository.save(book);
        return mapToDTO(updated);
    }

    @Override
    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + id));
        bookRepository.deleteById(book.getId());
    }

    private BookResponseDTO mapToDTO(Book book) {
        return BookResponseDTO.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .summary(book.getSummary())
                .description(book.getDescription())
                .coverImageUrl(book.getCoverImageUrl())
                .build();
    }
}
