package com.example.library.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.library.entity.Book;
import com.example.library.repository.BookRepository;

@Service
public class BookService {


private final BookRepository bookRepository;

public BookService(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
}

// Add book
public Book addBook(Book book) {
    book.setAvailable(true);
    book.setDeleted(false);
    return bookRepository.save(book);
}

// Get all active books
public List<Book> getAllBook() {
    return bookRepository.findByDeletedFalse();
}
// Get book by ID
public Book getBookById(int id) {
    Book book = bookRepository.findById(id).orElse(null);

    if (book == null || book.isDeleted()) {
        return null;
    }

    return book;
}

// Soft delete book
public void deleteBook(int id) {
    Book book = bookRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Book not found."));

    if (book.isDeleted()) {
        throw new RuntimeException("Book is already deleted.");
    }

    book.setDeleted(true);
    bookRepository.save(book);
}


}
