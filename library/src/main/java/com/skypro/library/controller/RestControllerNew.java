package com.skypro.library.controller;

import com.skypro.library.entity.Book;
import com.skypro.library.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("skypro")
public class RestControllerNew {
    private BookService bookService;

    public RestControllerNew(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/api/book")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping("/api/book")
    public void createBook(@RequestParam Book book) {
        bookService.createNewBook(book);
    }

    @PutMapping("/api/book")
    public Book editBook(@RequestParam Book book) {
        bookService.editBook(book);
        return book;
    }

    @DeleteMapping("/api/book?isbn=<isbn>")
    public void deleteBook(@RequestParam Book book) {
        bookService.deleteBook(book);
    }
}
