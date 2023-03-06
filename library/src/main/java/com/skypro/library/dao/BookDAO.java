package com.skypro.library.dao;
import com.skypro.library.entity.Book;
import java.util.List;

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
