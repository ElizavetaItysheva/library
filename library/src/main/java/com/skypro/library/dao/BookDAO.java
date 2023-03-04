package com.skypro.library.dao;

import com.skypro.library.entity.Book;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BookDAO {
    // добавление книги
    void addBook( Book book);
    //
    void updateBook(Book book);
    //
    void deleteBookById(String isbn);
    //
    List<Book> getAllOfBooks();
    Book getBookByIsbn(String isbn);
}
