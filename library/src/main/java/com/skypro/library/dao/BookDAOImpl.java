package com.skypro.library.dao;

import com.skypro.library.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;


public class BookDAOImpl implements BookDAO {
private final JdbcTemplate jdbcTemplate;

    public BookDAOImpl(JdbcTemplate jdbcTemplate ) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addBook( Book book ) {
    this.jdbcTemplate.update(
            "INSERT INTO books (name, author, year, isbn) VALUES (?, ?, ?, ?)",
            book.getTitle(), book.getAuthor(), book.getYear(), book.getIsbn());
    }

    @Override
    public void updateBook(Book book ) {
        jdbcTemplate.update("UPDATE books SET name=?, author=?, year=? WHERE isbn=?", book.getTitle(),
                book.getAuthor(), book.getYear(), book.getIsbn());
    }

        @Override
    public void deleteBookById( String isbn ) {
            jdbcTemplate.update("DELETE FROM books WHERE isbn=?", isbn);
        }

    @Override
    public List<Book> getAllOfBooks() {
        return jdbcTemplate.query("SELECT * FROM books", new BeanPropertyRowMapper<>(Book.class));
    }

    @Override
    public Book getBookByIsbn(String isbn) {
        return jdbcTemplate.query("SELECT * FROM books WHERE isbn=?", new Object[]{isbn}, new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }
}
