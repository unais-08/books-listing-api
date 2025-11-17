package com.booklist.app.config;

import com.booklist.app.dto.BookRequestDTO;
import com.booklist.app.service.impl.BookServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSeeder implements CommandLineRunner {

        private final BookServiceImpl bookService;

        public DataSeeder(BookServiceImpl bookService) {
                this.bookService = bookService;
        }

        @Override
        public void run(String... args) throws Exception {

                if (bookService.getAllBooks().size() > 0) {
                        return; // prevent duplicate seeding
                }

                bookService.createBook(new BookRequestDTO("The Alchemist", "Paulo Coelho", "A journey of dreams",
                                "A philosophical story about following your destiny."));
                bookService.createBook(new BookRequestDTO("Atomic Habits", "James Clear", "Habit building",
                                "Guide to building and breaking habits."));
                bookService.createBook(new BookRequestDTO("Clean Code", "Robert C. Martin", "Code quality",
                                "Best practices for writing clean and maintainable code."));
                bookService.createBook(new BookRequestDTO("Rich Dad Poor Dad", "Robert Kiyosaki", "Finance basics",
                                "Personal finance and wealth building lessons."));
                bookService.createBook(new BookRequestDTO("The Pragmatic Programmer", "Andrew Hunt", "Software craft",
                                "Techniques and practices to improve software development."));
                bookService.createBook(
                                new BookRequestDTO("Deep Work", "Cal Newport", "Focus",
                                                "Rules for focused and productive work."));
                bookService.createBook(new BookRequestDTO("Thinking, Fast and Slow", "Daniel Kahneman", "Psychology",
                                "Insights into how humans think and make decisions."));
                bookService.createBook(new BookRequestDTO("Dune", "Frank Herbert", "Sci-fi",
                                "A classic science-fiction story about politics, religion, and destiny."));
                bookService.createBook(new BookRequestDTO("The Hobbit", "J.R.R. Tolkien", "Adventure",
                                "Epic adventure in Middle-earth before LOTR."));
                bookService.createBook(new BookRequestDTO("1984", "George Orwell", "Dystopian",
                                "Classic dystopian novel on surveillance and control."));
        }
}
