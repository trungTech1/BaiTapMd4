package com.example.demo.interfaces;

import com.example.demo.model.Book;

import java.util.List;

public interface IBookService {
    List<Book> getAllBooks();
    Book getBookById(Integer id);
    void saveBook(Book book);
    void deleteBook(Integer id);
    List<Book> getBooksByCategoryId(Integer categoryId);

}
