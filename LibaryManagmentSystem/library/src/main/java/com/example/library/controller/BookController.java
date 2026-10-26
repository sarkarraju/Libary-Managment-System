package com.example.library.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.entity.Book;
import com.example.library.service.BookService;

@RestController
public class BookController {

private final BookService bookService;

public BookController(BookService bookService) {
    this.bookService = bookService;
}

// Add book
@PostMapping("/api/books")
public Book addBook(@RequestBody Book book) {
    return bookService.addBook(book);
}

// Get all books
@GetMapping("/api/books")
public List<Book> getAllBook() {
    return bookService.getAllBook();
}

// Get book by ID
@GetMapping("/api/books/{id}")
public Book getBookById(@PathVariable int id) {
    return bookService.getBookById(id);
}

// Delete book
@DeleteMapping("/api/books/{id}")
public ResponseEntity<String> deleteBook(@PathVariable int id) {
    try {
        bookService.deleteBook(id);
        return ResponseEntity.ok("Book deleted successfully.");
    } catch (RuntimeException e) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(e.getMessage());
    }
}


}
