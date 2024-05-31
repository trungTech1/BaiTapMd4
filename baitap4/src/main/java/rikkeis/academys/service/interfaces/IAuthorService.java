package rikkeis.academys.service.interfaces;

import rikkeis.academys.model.Author;
import rikkeis.academys.model.Catalog;

import java.util.List;

public interface IAuthorService {
    List<Author> getAllAuthor();
    Author getAuthorById(int id);
    void addAuthor(Author author);
    void save(Author author);
    void deleteAuthor(int id);
    void changeAuthorStatus(int authorId, boolean status);

}
