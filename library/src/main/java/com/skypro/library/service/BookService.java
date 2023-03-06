package com.skypro.library.service;

import com.skypro.library.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();
    void createNewBook(Book book);
    Book editBook(Book book);
    void deleteBook(String isbn);
}
