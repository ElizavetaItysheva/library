package com.skypro.library.service;

import com.skypro.library.dao.BookDAOImpl;
import com.skypro.library.entity.Book;
import com.skypro.library.exception.ValidationException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookServiceImpl implements BookService{
    private BookDAOImpl bookDAOImpl;
    @Override
    public List<Book> getAllBooks() {
        return bookDAOImpl.getAllOfBooks();
    }

    @Override
    public void createNewBook( Book book ) {
        validateBook(book);
bookDAOImpl.addBook(book);
    }

    @Override
    public Book editBook( Book book ) {
        if(book == null){
            throw  new NullPointerException("такой книги нет!");
        }
        // Получем книгу по ISBN
        Book editableBook = this.bookDAOImpl.getBookByIsbn(book.getIsbn());
        // Устанавливаем поля которые можно обновлять
        // ISBN - это идентификатор книги - поэтому меняться он не может
        editableBook.setAuthor(book.getAuthor());
        editableBook.setTitle(book.getTitle());
        editableBook.setYear(book.getYear());
        //Запускаем метод валидации после редактирования
        validateBook(editableBook);
        //Обновляем книгу в БД
        bookDAOImpl.updateBook(book);
        return book;
    }

    @Override
    public void deleteBook( Book book ) {
bookDAOImpl.deleteBookById(book.getIsbn());
    }
    private void validateBook(Book book){
        String currIsbn = book.getIsbn();
        //1.удаляем все дефисы

        currIsbn = currIsbn.replace("-", "");
        //2.проверка на правильность строки, чтобы там были только цифры
        if(!currIsbn.matches("[0-9]+")){
            throw new ValidationException("Неправильный isbn код!(код должен содержать только цифры)");
        }
        //3. проверка на длину строки
        if(currIsbn.length() == 13){
            throw new ValidationException("Неправильный isbn код!(код должен быть из 13 символов)");
        }

        // расчет суммы 12 цифр

            int sum =0;
            for (int i = 0; i < 12; i++) {
                int current = currIsbn.charAt(i);
                if(current%2 ==0){
                    sum+= current * 1;
                } else {
                    sum+= current *3;
                }
            }
            // достаем последнюю цифру
        int last = currIsbn.charAt(currIsbn.length()-1);
            if(!(10 - (sum % 10) == last)){
                throw new ValidationException("isbn код не верный!");
            }
        }
    }

