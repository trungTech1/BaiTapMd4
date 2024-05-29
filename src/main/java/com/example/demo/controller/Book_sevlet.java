package com.example.demo.controller;

import com.example.demo.interfaces.IBookService;
import com.example.demo.interfaces.ICategoryService;
import com.example.demo.model.Book;
import com.example.demo.model.Category;
import com.example.demo.service.BookServicelmpl;
import com.example.demo.service.CategoryService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Book_sevlet", value = "/Book_sevlet")
public class Book_sevlet extends HttpServlet {
    private static final IBookService bookService = new BookServicelmpl();
    private static final ICategoryService categoryService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "LISTBOOK":
                    List<Book> books = bookService.getAllBooks();
                    request.setAttribute("books", books);
                    request.getRequestDispatcher("views/book_papes/Bookmanager.jsp").forward(request, response);
                    break;
                case "ADDBOOK":
                    List<Category> categorys = categoryService.getAllCategory();
                    request.setAttribute("categorys", categorys);
                    request.getRequestDispatcher("views/book_papes/Addbook.jsp").forward(request, response);
                    break;
                case "EDITBOOK":
                    Integer id = Integer.parseInt(request.getParameter("id"));
                    Book book = bookService.getBookById(id);
                    List<Category> categorysEdit = categoryService.getAllCategory();
                    request.setAttribute("categorys", categorysEdit);
                    request.setAttribute("book", book);
                    request.getRequestDispatcher("views/book_papes/Editbook.jsp").forward(request, response);
                    break;
                case "DELETEBOOK":
                    bookService.deleteBook(Integer.parseInt(request.getParameter("id")));
                    response.sendRedirect("Book_sevlet?action=LISTBOOK");
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "ADDBOOK":
                    bookService.saveBook(getBook(request));
                    response.sendRedirect("Book_sevlet?action=LISTBOOK");
                    break;
                case "EDIT":
                    Integer idEdit = Integer.parseInt(request.getParameter("id"));
                    Book book = getBook(request);
                    book.setId(idEdit);
                    bookService.saveBook(book);
                    response.sendRedirect("Book_sevlet?action=LISTBOOK");
                    break;
                default:
                    break;
            }
        }
    }

    private Book getBook(HttpServletRequest request) {
        Integer categoryId = null;
        String categoryIdStr = request.getParameter("category");
        System.out.println("cateId" + categoryIdStr);
        if (categoryIdStr != null && !categoryIdStr.isEmpty()) {
            categoryId = Integer.parseInt(categoryIdStr);
        }

        String name = request.getParameter("bookname");
        System.out.println("name" + name);
        String author = request.getParameter("author");

        Integer stock = null;
        String stockStr = request.getParameter("stock");
        System.out.println("stock" + stockStr);
        if (stockStr != null && !stockStr.isEmpty()) {
            stock = Integer.parseInt(stockStr);
        }

        Integer totalPages = null;
        String totalPagesStr = request.getParameter("totalpages");
        if (totalPagesStr != null && !totalPagesStr.isEmpty()) {
            totalPages = Integer.parseInt(totalPagesStr);
        }

        Integer yearCreated = null;
        String yearCreatedStr = request.getParameter("yearcreate");
        if (yearCreatedStr != null && !yearCreatedStr.isEmpty()) {
            yearCreated = Integer.parseInt(yearCreatedStr);
        }

        Double price = null;
        String priceStr = request.getParameter("Price");
        if (priceStr != null && !priceStr.isEmpty()) {
            price = Double.parseDouble(priceStr);
        }

        Boolean status = null;
        String statusStr = request.getParameter("status");
        if (statusStr != null && !statusStr.isEmpty()) {
            status = Boolean.parseBoolean(statusStr);
        }

        return new Book(null, categoryId, name, price, stock, totalPages, yearCreated, author, status);
    }
}