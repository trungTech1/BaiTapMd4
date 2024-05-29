package com.example.demo.service;

import com.example.demo.dao.BookDao;
import com.example.demo.interfaces.IBookDao;
import com.example.demo.interfaces.IBookService;
import com.example.demo.model.Book;
import com.google.protobuf.BoolValueOrBuilder;

import java.util.List;

public class BookServicelmpl implements IBookService {
    private static final IBookDao BookDao = new BookDao();

    @Override
    public List<Book> getAllBooks() {
        return BookDao.getAllBooks();
    }

    @Override
    public Book getBookById(Integer id) {
        return BookDao.getBookById(id);
    }

    @Override
    public void saveBook(Book book) {
        BookDao.saveBook(book);
    }

    @Override
    public void deleteBook(Integer id) {
        BookDao.deleteBook(id);
    }

    @Override
    public List<Book> getBooksByCategoryId(Integer categoryId) {
        return null;
    }
}
