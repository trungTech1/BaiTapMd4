package rikkeis.academys.service.interfaces;

import rikkeis.academys.model.Book;
import rikkeis.academys.model.Catalog;

import java.util.List;

public interface IBookService {
    List<Book> getAllBook();
    Book getBookById(int id);
    void save(Book book);
    void updateBook(Book book);
    void deleteBook(int id);
    void changeBookStatus(int bookId, boolean status);

}
