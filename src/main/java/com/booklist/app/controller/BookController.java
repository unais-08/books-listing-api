package com.booklist.app.controller;

import com.booklist.app.dto.BookRequestDTO;
import com.booklist.app.dto.BookResponseDTO;
import com.booklist.app.response.ApiResponse;
import com.booklist.app.response.ResponseBuilder;
import com.booklist.app.service.impl.BookServiceImpl;

import jakarta.validation.Valid;
// import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "*")

public class BookController {

    private final BookServiceImpl bookService;
    private static final Logger log = LoggerFactory.getLogger(BookController.class);

    public BookController(BookServiceImpl bookService) {
        this.bookService = bookService;
    }

    // Get all books
    @GetMapping
    public ResponseEntity<ApiResponse<List<BookResponseDTO>>> getAll() {
        log.info("Fetching all books");
        log.debug("Calling service: getAllBooks()");

        List<BookResponseDTO> books = bookService.getAllBooks();
        log.debug("Total books fetched: {}", books.size());

        return ResponseBuilder.build(HttpStatus.OK, "Books fetched successfully", books);
    }

    // Get a single book by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<BookResponseDTO>> getById(@PathVariable Long id) {
        log.info("Fetching book with id: {}", id);
        log.debug("Calling service: getBook({})", id);

        BookResponseDTO response = bookService.getBook(id);
        log.debug("Fetched book response: {}", response);

        return ResponseBuilder.build(HttpStatus.OK, "Book fetched successfully", response);
    }

    // Create a new book
    @PostMapping
    public ResponseEntity<ApiResponse<BookResponseDTO>> create(@Valid @RequestBody BookRequestDTO dto) {
        log.info("Creating a new book");
        log.debug("Incoming book payload: {}", dto);

        BookResponseDTO response = bookService.createBook(dto);
        log.debug("Created book response: {}", response);

        return ResponseBuilder.build(HttpStatus.CREATED, "Book created successfully", response);
    }

    // Update an existing book
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<BookResponseDTO>> update(
            @PathVariable Long id,
            @Valid @RequestBody BookRequestDTO dto) {

        log.info("Updating book with id: {}", id);
        log.debug("Incoming update payload: {}", dto);

        BookResponseDTO response = bookService.updateBook(id, dto);
        log.debug("Updated book response: {}", response);

        return ResponseBuilder.build(HttpStatus.OK, "Book updated successfully", response);
    }

    // Delete a book
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable Long id) {
        log.warn("Deleting book with id: {}", id);
        log.debug("Calling service: deleteBook({})", id);

        bookService.deleteBook(id);
        log.debug("Book with id {} deleted successfully", id);

        return ResponseBuilder.build(
                HttpStatus.OK,
                "Book deleted successfully",
                "Deleted book with id: " + id);
    }
}
