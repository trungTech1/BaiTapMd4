package rikkeis.academys.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.stereotype.Controller;
import rikkeis.academys.model.Author;
import rikkeis.academys.model.Book;
import rikkeis.academys.service.AuthorService;
import rikkeis.academys.service.BookService;
import rikkeis.academys.service.CatalogService;

@Controller
public class GetControllers {


    @GetMapping("/list-book")
    public  String book(Model model) {
   BookService bookService = new BookService();
        model.addAttribute("books",bookService.getAllBook());
        return "book/list-book";
    }

    @GetMapping("/add-book")
    public String author() {
        return "book/add-book";
    }

    @GetMapping("/edit-book/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        BookService bookService = new BookService();
        Book book = bookService.getBookById(id);
        System.out.println("id: " + book);
        model.addAttribute("books", book);
        return "book/edit-book";
    }

    @GetMapping("/delete-book/{id}")
    public String deleteBook(@PathVariable("id") Integer id) {
        BookService bookService = new BookService();
        bookService.deleteBook(id);
        return "redirect:/list-book";
    }

    @GetMapping("/list-author")
    public String author(Model model) {
        AuthorService author = new AuthorService();
        model.addAttribute("authors", author.getAllAuthor());
        return "author/list-author";
    }

    @GetMapping("/add-author")
    public String addAuthor() {

        return "author/add-author";
    }

    @GetMapping("/edit-author/{id}")
    public String showEditAuthor (@PathVariable("id") Integer id, Model model) {
        AuthorService authorService = new AuthorService();
        Author author = authorService.getAuthorById(id);
        model.addAttribute("authors", author);
        return "author/edit-author";
    }

    @GetMapping("/delete-author/{id}")
    public String deleteAuthor(@PathVariable("id") Integer id) {
        AuthorService authorService = new AuthorService();
        authorService.deleteAuthor(id);
        return "redirect:/list-author";
    }

    @GetMapping("/list-catalog")
    public String catalog(Model model) {
        CatalogService catalogService = new CatalogService();
        model.addAttribute("catalogs", catalogService.getAllCatalogs());
        return "catalog/list-catalog";
    }
    @GetMapping("/add-catalog")
    public String addCatalog() {
        return "catalog/add-catalog";
    }
    @GetMapping("/edit-catalog/{id}")
    public String showEditCatalog(@PathVariable("id") Integer id, Model model) {
        CatalogService catalogService = new CatalogService();
        model.addAttribute("catalogs", catalogService.getCatalogById(id));
        return "catalog/edit-catalog";
    }

    @GetMapping("/delete-catalog/{id}")
    public String deleteCatalog(@PathVariable("id") Integer id) {
        CatalogService catalogService = new CatalogService();

        catalogService.deleteCatalog(id);
        return "redirect:/list-catalog";
    }





}

