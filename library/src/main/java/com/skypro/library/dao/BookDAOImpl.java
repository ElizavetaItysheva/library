package com.skypro.library.dao;

import com.skypro.library.entity.Book;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDAOImpl implements BookDAO {
private final JdbcTemplate jdbcTemplate;

    public BookDAOImpl(JdbcTemplate jdbcTemplate ) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addBook( Book book ) {
    this.jdbcTemplate.update(
            "INSERT INTO books (name, author, year, isbn) VALUES (?, ?, ?, ?)",
            book.getName(), book.getAuthor(), book.getYear(), book.getIsbn());
    }

    @Override
    public void updateBook(Book book ) {
        jdbcTemplate.update("UPDATE books SET name=?, author=?, year=? WHERE isbn=?", book.getName(),
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
