package rikkeis.academys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import rikkeis.academys.model.Author;
import rikkeis.academys.model.Book;
import rikkeis.academys.model.Catalog;
import rikkeis.academys.service.AuthorService;
import rikkeis.academys.service.BookService;
import rikkeis.academys.service.CatalogService;

@Controller
public class PostControllers {
    @PostMapping("/add-book")
    public String addBook(@ModelAttribute Book book) {
        BookService bookService = new BookService();
        bookService.save(book);
        return "redirect:/list-book";
    }

    @PostMapping("/edit-book")
    public String editBook(@ModelAttribute Book book) {
        BookService bookService = new BookService();
        bookService.save(book);
        return "redirect:/list-book";
    }

    @PostMapping("/add-author")
    public String addAuthor(@ModelAttribute Author author) {
        AuthorService authorService = new AuthorService();
        authorService.save(author);
        return "redirect:/list-author";
    }
    @PostMapping("/edit-author")
    public String editBook(@ModelAttribute Author author) {
        AuthorService authorService = new AuthorService();
        authorService.save(author);
        return "redirect:/list-author";
    }
    @PostMapping("/add-catalog")
    public String addCatalog(@ModelAttribute Catalog catalog) {
        CatalogService catalog1 = new CatalogService();
        catalog1.save(catalog);
        return "redirect:/list-catalog";
    }
    @PostMapping("/edit-catalog")
    public String editCatalog(@ModelAttribute Catalog catalog) {
        CatalogService catalog1 = new CatalogService();
        catalog1.save(catalog);
        return "redirect:/list-catalog";
    }

}
