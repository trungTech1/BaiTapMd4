package com.example.demo.dao;

import com.example.demo.interfaces.IBookDao;
import com.example.demo.model.Book;
import com.example.demo.utill.ConnectDb;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookDao implements IBookDao {

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        Connection connec = ConnectDb.getConnection();
        try{
            CallableStatement cs = connec.prepareCall("SELECT * FROM books");
            ResultSet rs = cs.executeQuery();
            while (rs.next()){
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setName(rs.getString("name"));
                book.setAuthor(rs.getString("author"));
                book.setStock(rs.getInt("stock"));
                book.setTotalPages(rs.getInt("totalPages"));
                book.setYearCreated(rs.getInt("yearCreated"));
                book.setPrice(rs.getDouble("price"));
                book.setCategory_id(rs.getInt("category_id"));
                book.setStatus(rs.getBoolean("status"));
                books.add(book);
                System.out.println(book.toString());
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            ConnectDb.closeConnection(connec);
        }
        return books;
    }

    @Override
    public Book getBookById(Integer id) {
        Book book = new Book();
        Connection connec = ConnectDb.getConnection();
        try{
            CallableStatement cs = connec.prepareCall("SELECT books.*, categories.name as category_name FROM books LEFT JOIN categories ON books.category_id = categories.id WHERE books.id = ?");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()){
                book.setId(rs.getInt("id"));
                book.setName(rs.getString("name"));
                book.setAuthor(rs.getString("author"));
                book.setStock(rs.getInt("stock"));
                book.setTotalPages(rs.getInt("totalPages"));
                book.setYearCreated(rs.getInt("yearCreated"));
                book.setPrice(rs.getDouble("price"));
                book.setCategory_id(rs.getInt("category_id"));
                book.setStatus(rs.getBoolean("status"));
            }

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            ConnectDb.closeConnection(connec);
        }
        return book;
    }

    @Override
    public void saveBook(Book book) {
        Connection connec = ConnectDb.getConnection();
        CallableStatement cs = null;
        if(book.getId() !=null){
            try {
                cs = connec.prepareCall("UPDATE books SET name = ?, author = ?, stock = ?, totalPages = ?, yearCreated = ?, price = ?, category_id = ?, status = ? WHERE id = ?");
                cs.setString(1, book.getName());
                cs.setString(2, book.getAuthor());
                cs.setInt(3, book.getStock());
                cs.setInt(4, book.getTotalPages());
                cs.setInt(5, book.getYearCreated());
                cs.setDouble(6, book.getPrice());
                cs.setInt(7, book.getCategory_id());
                cs.setBoolean(8, book.getStatus());
                cs.setInt(9, book.getId());
                cs.executeUpdate();
            }catch (Exception e) {
                e.printStackTrace();
            }finally {
                ConnectDb.closeConnection(connec);
            }
        }
        else {
            try {
                cs = connec.prepareCall("INSERT INTO books(name, author, stock, totalPages, yearCreated, price, category_id, status) VALUES(?,?,?,?,?,?,?,?)");
                cs.setString(1, book.getName());
                cs.setString(2, book.getAuthor());
                cs.setInt(3, book.getStock());
                cs.setInt(4, book.getTotalPages());
                cs.setInt(5, book.getYearCreated());
                cs.setDouble(6, book.getPrice());
                cs.setInt(7, book.getCategory_id());
                cs.setBoolean(8, book.getStatus());
                cs.executeUpdate();
            }catch (Exception e) {
                e.printStackTrace();
            }finally {
                ConnectDb.closeConnection(connec);
            }
        }
    }

    @Override
    public void deleteBook(Integer id) {
        Connection connec = ConnectDb.getConnection();
        try {
           CallableStatement cs = connec.prepareCall("DELETE FROM books WHERE id = ?");
            cs.setInt(1, id);
            cs.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            ConnectDb.closeConnection(connec);
        }

    }

    @Override
    public List<Book> getBooksByCategoryId(Integer categoryId) {
        return null;
    }
}
